package isr;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import backend.ui.UIBasicGameState;

public class GamePlay extends UIBasicGameState
{
	private static Color bgColor = new Color(0, 64, 128);

	@Override
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException
	{
		// TODO Auto-generated method stub

	}
	
	@Override
	public void enter(GameContainer container, StateBasedGame game)
			throws SlickException
	{
		super.enter(container, game);
	}

	@Override
	protected void createUI(GameContainer container, StateBasedGame game)
			throws SlickException
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void render(GameContainer gc, StateBasedGame game, Graphics gfx)
			throws SlickException
	{
		gfx.setBackground(bgColor);
		
		String text = "TAMEEEEEE";
		gfx.drawString(text, 
				(gc.getWidth() - gfx.getFont().getWidth(text)) / 2, 
				(gc.getHeight() - gfx.getFont().getLineHeight()) / 2);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame game, int delta)
			throws SlickException
	{
		// TODO Auto-generated method stub

	}

	@Override
	public int getID()
	{
		return Game.GAME_PLAY;
	}

}


