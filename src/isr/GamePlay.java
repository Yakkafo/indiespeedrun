package isr;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.Log;

import backend.Counter;
import backend.KeySequenceDetector;
import backend.MathHelper;
import backend.MouseCursor;
import backend.audio.MusicPlayer;
import backend.geom.Vector2i;
import backend.ui.IActionListener;
import backend.ui.Label;
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
	private MouseCursor cursForbid;
	private MouseCursor defaultCursor;
	private DescriptionBar descript;
	private ProgressBar progress;
	private KeySequenceDetector kDetector;
	private NextTurnButton btn;
	private Report report;
	private Animation watchAnim;
	private Counter resoPhaseCounter;
	private int nextState;
	
	private int progression;
	private static final int PROGRESSION_GOAL = 1000;
	private static final int ENEMY_SPEED = 70;
	private Image background;
	private Sound waitingSound;
	private boolean resolutionPhase;
	private boolean reportPhase;

	@Override
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException
	{
		cursEngine = new MouseCursor(new Image(Game.ASSETS_DIR+"cursor_engine.png"), 0, 0);
		cursCell = new MouseCursor(new Image(Game.ASSETS_DIR+"cursor_cell.png"), 0, 0);
		cursHold = new MouseCursor(new Image(Game.ASSETS_DIR+"cursor_hold.png"), 0, 0);
		cursCommon = new MouseCursor(new Image(Game.ASSETS_DIR+"cursor_common.png"), 0, 0);
		cursDorm = new MouseCursor(new Image(Game.ASSETS_DIR+"cursor_dorm.png"), 0, 0);
		defaultCursor = new MouseCursor(new Image(Game.ASSETS_DIR+"cursor.png"), 0, 0);
		cursForbid = new MouseCursor(new Image(Game.ASSETS_DIR+"cursor_forbid.png"), 0, 0);
		currentCurs = defaultCursor;
		resolutionPhase = false;
		reportPhase = false;
		progression = 0;
		waitingSound = new Sound(Game.ASSETS_DIR + "clic_next_turn.ogg");
		background = new Image(Game.ASSETS_DIR + "fond.png");
		watchAnim = new Animation(new SpriteSheet(Game.ASSETS_DIR + "montre.png", 150, 150), 100);
		
		kDetector = new KeySequenceDetector(KeySequenceDetector.KONAMI_CODE);
	}
	
	@Override
	public void enter(GameContainer container, StateBasedGame game)
			throws SlickException
	{
		nextState = -1;
		super.enter(container, game);
		
	}

	@Override
	protected void createUI(GameContainer container, StateBasedGame game)
			throws SlickException
	{
		ui = new RootPane(container.getWidth(), container.getHeight()); // ecran

		// Bouton next turn
		btn = new NextTurnButton(ui, 820, 605);
		btn.addActionListener(new IActionListener() {
			@Override
			public void actionPerformed(Widget sender) {
				report.cleanReport();
				Ship.get().turn(report);
				EnemyShip.get().advance();
				resolutionPhase = true;
				resoPhaseCounter = new Counter(2500);
				waitingSound.play();
				report.generateReport();
				if(isWin())
					nextState = Game.GAME_WIN;
			}
		});
		ui.add(btn);
		
		//Progress bar
		progress = new ProgressBar(ui, 100, 25, 800, 15);
		ui.add(progress);
		
		//Description
		descript = new DescriptionBar(ui, 0, 550, 750, 200);
		ui.add(descript);
		
		//Report
		report = new Report(ui, 200, 0, 544, 750);
		ui.add(report);

	}

	@Override
	public void render(GameContainer gc, StateBasedGame game, Graphics gfx)
			throws SlickException
	{
		gfx.setBackground(bgColor);
		ScrollBackground.get().render(gfx);
		
		gfx.pushTransform();		
		gfx.translate(viewOffset.x, viewOffset.y);
		
		Ship.get().render(gc, game, gfx);
				
		currentCurs.use(gc);

		gfx.popTransform();
		
		if(resolutionPhase)
			gfx.drawAnimation(watchAnim, 820, 570);
	}
	
	public boolean isWin()
	{
		return Ship.get().getProgressMiles() >= Ship.TRIP_DISTANCE;
	}
	
	public boolean isLoose()
	{
		return EnemyShip.get().reachedPlayer();
	}

	@Override
	public void update(GameContainer gc, StateBasedGame game, int delta)
			throws SlickException
	{
		if(nextState > 0)
			game.enterState(nextState);
		
		Vector2i shipSize = Ship.get().getBackgroundSize();
		viewOffset.x = (gc.getWidth() - shipSize.x) / 2;
		viewOffset.y = (gc.getHeight() - shipSize.y) / 2;
		// Add somme floating
		float t = (float)gc.getTime() / 1000.f;
		viewOffset.x += 8.f * (float)Math.cos(t);
		viewOffset.y += 4.f * (float)Math.sin(2.f * t);
		//report
		if(resolutionPhase && resoPhaseCounter.update(delta))
		{
			resolutionPhase = false;
			reportPhase = true;
			report.setVisible(true);
		}
		if(resolutionPhase || reportPhase)
		{
			btn.setVisible(false);
			descript.setVisible(false);
		}
		else
		{
			descript.setVisible(true);
			btn.setVisible(true);
		}
		
		// Face avatar
		if(selectedCharacter != null)
			descript.setAvatar(selectedCharacter.getAvatar());
		else
			descript.setAvatar(null);
		
		ScrollBackground.get().update(delta);
		// Play music
		if(!Sounds.music.playing() && !Sounds.kMusic.playing())
			MusicPlayer.get().loop(Sounds.music, 0.5f);
		
		if(!report.isVisible() && isLoose())
			game.enterState(Game.GAME_LOOSE);
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
		if(!resolutionPhase && !reportPhase)
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
				boolean forbid = r.isFull();
				if(sec)
				{
					switch(r.getType())
					{
					case ENGINE :
						if(forbid)
							currentCurs = cursForbid;
						else
							currentCurs = cursEngine;
						break;
					case CELL :
						if(forbid)
							currentCurs = cursForbid;
						else
							currentCurs = cursCell;
						break;
					case HOLD :
						if(forbid)
							currentCurs = cursForbid;
						else
							currentCurs = cursHold;
						break;
					case COMMON :
						if(forbid)
							currentCurs = cursForbid;
						else
							currentCurs = cursCommon;
						break;
					case DORM :
						if(forbid)
							currentCurs = cursForbid;
						else
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
				//description = hoveredCharacter.getReport();
			}
			
			descript.setText(description);
		}
		else
			currentCurs = defaultCursor;
	}
	
	@Override
	public void mousePressed(int button, int x, int y) 
	{
		if(!resolutionPhase && !reportPhase)
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
		if(reportPhase && button == Input.MOUSE_LEFT_BUTTON) //Characters selection
		{
			reportPhase = false;
			report.setVisible(false);
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


