package isr;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import backend.MathHelper;

public class Spy {
	
	private Image img;
	private static final float PROBABILITY_BAD_ACTION = 0.25f; 
	public static final int BAD_ACTION = 40; 
	
	public Spy()
	{
		try {
			img = new Image(Game.ASSETS_DIR+"espion.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void render(GameContainer gc, StateBasedGame game, Graphics gfx)
	{
		img.draw(575, 250);
	}
	
	/**
	 * Tests with a probability-cast if the spy escapes/damage the ship,
	 * assuming he is not under surveillance.
	 * @return
	 */
	public boolean isDoingBadAction()
	{
		return MathHelper.randFloat(0, 1) <= PROBABILITY_BAD_ACTION;
	}

}
