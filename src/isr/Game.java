package isr;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

import backend.ui.UIStateBasedGame;

public class Game extends UIStateBasedGame
{
	public static final String TITLE = "Our ISR game";
	public static final int SCREEN_WIDTH = 1000;
	public static final int SCREEN_HEIGHT = 700;
	public static final String ASSETS_DIR = "src/assets/";
	
	public static final int GAME_PLAY = 2;

	public static void main(String args[])
	{
		try
		{
			AppGameContainer gc = new AppGameContainer(new Game());
			gc.setDisplayMode(SCREEN_WIDTH, SCREEN_HEIGHT, false);
			gc.setTargetFrameRate(60);
			gc.setMaximumLogicUpdateInterval(200);
			gc.start();
		} 
		catch (SlickException e)
		{
			e.printStackTrace();
		}
		
	}

	public Game()
	{
		super(TITLE);
		this.addState(new GamePlay());
	}

	@Override
	public void initStatesList(GameContainer gc) throws SlickException
	{
		Ship.loadContent();
		Room.loadContent();
		Sounds.load();
		
		this.getState(GAME_PLAY).init(gc, this);
	}

}


