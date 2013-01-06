package isr;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import backend.ui.UIBasicGameState;

public class GameWin extends UIBasicGameState
{
	private int nextState;
	private Image img;

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException
	{
		img = new Image(Game.ASSETS_DIR + "victory.png");
	}
	
	@Override
	public void enter(GameContainer container, StateBasedGame game)
			throws SlickException
	{
		nextState = -1;
		super.enter(container, game);
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException
	{
		g.drawImage(img, 0, 0);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException
	{
		if(nextState > 0)
		{
			container.exit(); // TODO REMOVE THIS SHIT
			game.enterState(nextState);
		}
	}

	@Override
	protected void createUI(GameContainer container, StateBasedGame game)
			throws SlickException
	{
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mousePressed(int button, int x, int y)
	{
		nextState = Game.MAIN_MENU;
	}
	
	@Override
	public void keyPressed(int key, char c)
	{
		nextState = Game.MAIN_MENU;
	}
	
	@Override
	public int getID()
	{
		return Game.GAME_WIN;
	}

}


