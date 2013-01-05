package isr;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.Log;

import backend.KeySequenceDetector;
import backend.MathHelper;
import backend.MouseCursor;
import backend.audio.MusicPlayer;
import backend.geom.Vector2i;
import backend.ui.IActionListener;
import backend.ui.RootPane;
import backend.ui.UIBasicGameState;
import backend.ui.Widget;

public class GamePlay extends UIBasicGameState
{
	private static Color bgColor = new Color(0, 32, 64);

	private Character selectedCharacter;
	private Character hoveredCharacter;
	private Vector2f viewOffset = new Vector2f();
	private MouseCursor currentCurs;
	private MouseCursor cursEngine;
	private MouseCursor cursCell;
	private MouseCursor cursHold;
	private MouseCursor cursCommon;
	private MouseCursor cursDorm;
	private MouseCursor defaultCursor;
	private DescriptionBar descript;
	private ProgressBar progress;
	private KeySequenceDetector kDetector;
	
	private int progression;
	private static final int PROGRESSION_GOAL = 1000;
	private static final int PROGRESSION_FACTOR = 20;
	private static final int INITIAL_SPEED = 20;
	private static final int ENEMY_SPEED = 70;
	private Image background;

	@Override
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException
	{
		cursEngine = new MouseCursor(new Image("assets/cursor_engine.png"), 0, 0);
		cursCell = new MouseCursor(new Image("assets/cursor_cell.png"), 0, 0);
		cursHold = new MouseCursor(new Image("assets/cursor_hold.png"), 0, 0);
		cursCommon = new MouseCursor(new Image("assets/cursor_common.png"), 0, 0);
		cursDorm = new MouseCursor(new Image("assets/cursor_dorm.png"), 0, 0);
		defaultCursor = new MouseCursor(new Image("assets/cursor.png"), 0, 0);
		currentCurs = defaultCursor;

		progression = 0;

		background = new Image(Game.ASSETS_DIR + "fond.png");
		
		kDetector = new KeySequenceDetector(KeySequenceDetector.KONAMI_CODE);
	}
	
	@Override
	public void enter(GameContainer container, StateBasedGame game)
			throws SlickException
	{
		super.enter(container, game);
		
		Ship.get().init();
	}

	@Override
	protected void createUI(GameContainer container, StateBasedGame game)
			throws SlickException
	{
		ui = new RootPane(container.getWidth(), container.getHeight()); // ecran

		// Bouton next turn
		NextTurnButton btn = new NextTurnButton(ui, 820, 605);
		btn.addActionListener(new IActionListener() {
			@Override
			public void actionPerformed(Widget sender) {
				Ship.get().turn();
				progression += 
						INITIAL_SPEED + 
						Ship.get().getRoom(RoomType.ENGINE.ordinal()).getCharacterCount() * PROGRESSION_FACTOR;
				progress.setProgression(progression);
				progress.makeEnemyMove(ENEMY_SPEED);
				//SPY
				if(Ship.get().getRoom(RoomType.HOLD.ordinal()).getCharacterCount() == 0 && 
						Ship.get().getSpy().isDoingBadAction())
				{
					progress.makeEnemyMove(Spy.BAD_ACTION);
					System.out.println("Sabotage");
				} else if(Ship.get().getRoom(RoomType.HOLD.ordinal()).isTraitorInside() &&
						Ship.get().getSpy().isDoingBadAction())
				{
					progress.makeEnemyMove(Spy.BAD_ACTION);
					System.out.println("Sabotage");
				}
				//---
				///Check victory
				if(progress.isLost())
					System.out.println("PERDU!");
			}
		});
		ui.add(btn);
		
		//Progress bar
		progress = new ProgressBar(ui, 100, 25, 800, 15, PROGRESSION_GOAL, ENEMY_SPEED);
		ui.add(progress);
		
		//Description
		descript = new DescriptionBar(ui, 0, 550, 750, 200);
		ui.add(descript);

	}

	@Override
	public void render(GameContainer gc, StateBasedGame game, Graphics gfx)
			throws SlickException
	{
		gfx.setBackground(bgColor);
		renderBackground(gfx, gc);
		
		gfx.pushTransform();		
		gfx.translate(viewOffset.x, viewOffset.y);
		
		Ship.get().render(gc, game, gfx);
		
		currentCurs.use(gc);

		gfx.popTransform();
	}

	@Override
	public void update(GameContainer gc, StateBasedGame game, int delta)
			throws SlickException
	{
		Vector2i shipSize = Ship.get().getBackgroundSize();
		viewOffset.x = (gc.getWidth() - shipSize.x) / 2;
		viewOffset.y = (gc.getHeight() - shipSize.y) / 2;
		// Add somme bobbing?
		float t = (float)gc.getTime() / 1000.f;
		viewOffset.x += 8.f * (float)Math.cos(t);
		viewOffset.y += 4.f * (float)Math.sin(2.f * t);
		
		// Play music
		if(!Sounds.music.playing() && !Sounds.kMusic.playing())
			MusicPlayer.get().loop(Sounds.music, 1);
	}
	
	private void renderBackground(Graphics gfx, GameContainer gc)
	{
		float x0 = -(float)background.getWidth() * MathHelper.frac((float)gc.getTime() / 50000.f);
		gfx.drawImage(background, x0, 0, Color.gray);
		gfx.drawImage(background, x0 + background.getWidth(), 0, Color.gray);
		gfx.drawImage(background, x0 + 2*background.getWidth(), 0, Color.gray);
	}
	
	@Override
	public int getID()
	{
		return Game.GAME_PLAY;
	}
	
	private Vector2f convertToSceneCoords(int x, int y)
	{
		return new Vector2f(x - viewOffset.x, y - viewOffset.y);
	}
	
	@Override
	public void mouseMoved(int oldx, int oldy, int newx, int newy)
	{
		String description = "";
		Vector2f pos = convertToSceneCoords(newx, newy);
		Character c = Ship.get().getCharacterAt((int)pos.x, (int)pos.y);
		//cursor mouse
		Boolean sec = false;
		for(int i = 0; i < Ship.get().getCharactersSize(); i++)
		{
			sec = Ship.get().getCharacter(i).isSelected();
			if(sec)
				break;
		}
		Room r = Ship.get().getRoomAt((int)pos.x, (int)pos.y); // Get the room from click position
		if(r != null)
		{
			description = r.getReport();
			if(sec)
			{
				switch(r.getType())
				{
				case ENGINE :
					currentCurs = cursEngine;
					break;
				case CELL :
					currentCurs = cursCell;
					break;
				case HOLD :
					currentCurs = cursHold;
					break;
				case COMMON :
					currentCurs = cursCommon;
					break;
				case DORM :
					currentCurs = cursDorm;
					break;
				default :
					currentCurs = defaultCursor;
					break;
				}
			}			
		}
		else
			currentCurs = defaultCursor;
		//characters
		if(c != hoveredCharacter && hoveredCharacter != null)
			hoveredCharacter.setMouseOver(false);
		hoveredCharacter = c;
		if(hoveredCharacter != null)
		{
			hoveredCharacter.setMouseOver(true);
			description = hoveredCharacter.getReport();
		}
		
		descript.setText(description);

	}
	
	@Override
	public void mousePressed(int button, int x, int y) 
	{
		Vector2f pos = convertToSceneCoords(x, y);
		
		if(button == Input.MOUSE_LEFT_BUTTON) //Characters selection
		{
			// Unselect the last selected character
			if(selectedCharacter != null)
			{
				selectedCharacter.setSelected(false);
				selectedCharacter = null;
			}

			Character c = Ship.get().getCharacterAt((int)pos.x, (int)pos.y);
			if(c != null) // Character under the mouse !
			{
				c.setSelected(true);
				Log.debug(c.getName() + " has been selected.");
				selectedCharacter = c;
			}
			else
			{
				Log.debug("No character selected.");
			}
		}
		else if(button == Input.MOUSE_RIGHT_BUTTON)	// Send action to a character
		{
			if(selectedCharacter != null) // If we have selected a character
			{
				Room r = Ship.get().getRoomAt((int)pos.x, (int)pos.y); // Get the room from click position
				if(r != null)
				{
					// If room found, send the character to it
					//selectedCharacter.setNextAction(r);
					selectedCharacter.enterRoom(r);
					// Unselect the character
					selectedCharacter.setSelected(false);
					selectedCharacter = null;
					Log.debug("The room " + r.getType().name + " has been targeted.");
				}
			}
		}
	}
	
	@Override
	public void keyPressed(int key, char c)
	{
		if(kDetector.keyPressMatch(key))
		{
			MusicPlayer.get().play(Sounds.kMusic, 1);
		}
	}

}


