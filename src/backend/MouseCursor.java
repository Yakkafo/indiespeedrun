package backend;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class MouseCursor
{
	private static MouseCursor lastUsed;

	private int hotSpotX;
	private int hotSpotY;
	private Image image;
	
	public MouseCursor(Image image, int x, int y)
	{
		hotSpotX = x;
		hotSpotY = y;
		this.image = image;
	}
	
	/**
	 * Starts using the cursor directly on the game container
	 * @param gc
	 * @throws SlickException
	 */
	public void use(GameContainer gc) throws SlickException
	{
		if(lastUsed != this)
		{
			gc.setMouseCursor(image, hotSpotX, hotSpotY);
			lastUsed = this;
		}
	}
	
	/**
	 * Draws the cursor on the screen
	 * (useful if we don't override directly the OS cursor)
	 * @param gfx
	 * @param gc
	 */
	public void render(Graphics gfx, GameContainer gc)
	{
		gfx.drawImage(image,
				gc.getInput().getMouseX() - hotSpotX,
				gc.getInput().getMouseY() - hotSpotY);
	}

}


