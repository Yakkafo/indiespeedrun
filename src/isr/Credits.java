package isr;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import backend.ui.Label;
import backend.ui.RootPane;
import backend.ui.UIBasicGameState;

public class Credits extends UIBasicGameState
{
	private Image text;
	private int nextState;
	
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException
	{
		text = new Image(Game.ASSETS_DIR + "page_credit.png");
	}
	
	@Override
	public void enter(GameContainer container, StateBasedGame game)
			throws SlickException
	{
		nextState = -1;
		super.enter(container, game);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame game, Graphics gfx)
			throws SlickException
	{
		ScrollBackground.get().render(gfx);
		
		gfx.drawImage(text, 
				(gc.getWidth() - text.getWidth()) / 2,
				(gc.getHeight() - text.getHeight()) / 2);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException
	{
		if(nextState > 0)
			game.enterState(nextState);
		
		ScrollBackground.get().update(delta);
	}

	@Override
	protected void createUI(GameContainer container, StateBasedGame game)
			throws SlickException
	{
		ui = new RootPane(container.getWidth(), container.getHeight()); // ecran		
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
		return Game.CREDITS;
	}

}


