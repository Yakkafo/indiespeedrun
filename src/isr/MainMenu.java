package isr;

import org.newdawn.slick.GameContainer;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import backend.audio.MusicPlayer;
import backend.ui.IActionListener;
import backend.ui.Label;
import backend.ui.RootPane;
import backend.ui.UIBasicGameState;
import backend.ui.Widget;

public class MainMenu extends UIBasicGameState
{
	private int nextState; // 0 means Quit
	private Image startImage;
	private Image creditsImage;
	private Image quitImage;
	private Image titleImage;
	
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException
	{
		startImage = new Image(Game.ASSETS_DIR + "play.png");
		creditsImage = new Image(Game.ASSETS_DIR + "credit.png");
		quitImage = new Image(Game.ASSETS_DIR + "quit.png");
		titleImage = new Image(Game.ASSETS_DIR + "titre.png");
	}
	
	@Override
	public void enter(GameContainer container, StateBasedGame game)
			throws SlickException
	{
		nextState = -1;
		super.enter(container, game);
		
		if(!Sounds.music.playing())
			MusicPlayer.get().loop(Sounds.music, 1);
	}

	@Override
	protected void createUI(GameContainer container, StateBasedGame game)
			throws SlickException
	{
		ui = new RootPane(container.getWidth(), container.getHeight()); // ecran

		MenuButton startBtn = new MenuButton(ui, 0, 200, startImage);
		startBtn.addActionListener(new IActionListener() {
			@Override
			public void actionPerformed(Widget sender) {
				nextState = Game.GAME_PLAY;
			}
		});
		ui.add(startBtn);
		
		MenuButton creditsBtn = new MenuButton(ui, 0, 300, creditsImage);
		creditsBtn.addActionListener(new IActionListener() {
			@Override
			public void actionPerformed(Widget sender) {
				nextState = Game.CREDITS;
			}
		});
		ui.add(creditsBtn);
		
		MenuButton quitBtn = new MenuButton(ui, 0, 400, quitImage);
		quitBtn.addActionListener(new IActionListener() {
			@Override
			public void actionPerformed(Widget sender) {
				nextState = 0;
			}
		});
		ui.add(quitBtn);
		
		Label title = new Label(ui, 0, 50, titleImage);
		title.setAlign(Widget.ALIGN_CENTER, Widget.ALIGN_NONE, 0, 0);
		ui.add(title);
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics gfx)
			throws SlickException
	{
		ScrollBackground.get().render(gfx);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException
	{
		if(nextState > 0)
			game.enterState(nextState);
		else if(nextState == 0)
			container.exit();
		
		ScrollBackground.get().update(delta);
	}

	@Override
	public int getID()
	{
		return Game.MAIN_MENU;
	}

}
