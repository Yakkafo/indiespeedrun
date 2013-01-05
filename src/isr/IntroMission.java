package isr;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import backend.ui.UIBasicGameState;

public class IntroMission extends UIBasicGameState
{
	private Image missionImage;
	private int nextState;
//	private Music speech;
	
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException
	{
		missionImage = new Image(Game.ASSETS_DIR + "presentation.png");
//		speech = new Music(Game.ASSETS_DIR + "", true);
	}

	@Override
	public void enter(GameContainer container, StateBasedGame game)
			throws SlickException
	{
		nextState = -1;
		super.enter(container, game);
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics gfx)
			throws SlickException
	{
		gfx.drawImage(missionImage, 0, 0);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException
	{
		if(nextState > 0)
			game.enterState(nextState);
	}

	@Override
	protected void createUI(GameContainer container, StateBasedGame game)
			throws SlickException
	{
	}
	
	@Override
	public void mousePressed(int button, int x, int y)
	{
		nextState = Game.INTRO_DIVE;
	}
	
	@Override
	public void keyPressed(int key, char c)
	{
		if(key == Input.KEY_ESCAPE)
			nextState = Game.MAIN_MENU;
		else
			nextState = Game.INTRO_DIVE;
	}

	@Override
	public int getID()
	{
		return Game.INTRO_MISSION;
	}
	
}


