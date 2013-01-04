package backend;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;

import backend.geom.Rectangle;

/**
 * Simple utility for configuring the render bounds from a rectangle
 * @author Marc
 *
 */
public class View
{
	private Rectangle bounds;
	
	public View(Rectangle b)
	{
		bounds = new Rectangle(b);
	}
	
	public View(GameContainer gc)
	{
		bounds = new Rectangle(0, 0, gc.getWidth(), gc.getHeight());
	}
	
	public Rectangle getBounds()
	{
		return bounds;
	}
	
	public Vector2f convertCoords(GameContainer gc, float x, float y)
	{
		Vector2f pos = new Vector2f(
				bounds.getOriginX() + x * bounds.getWidth() / (float)gc.getWidth(),
				bounds.getOriginY() + y * bounds.getHeight() / (float)gc.getHeight());
		return pos;
	}
	
	public void look(Graphics gfx, GameContainer gc)
	{
		gfx.scale(gc.getWidth(), gc.getHeight());
		gfx.scale(1.f / (float)bounds.getWidth(), 1.f / (float)bounds.getHeight());
		gfx.translate(-bounds.left(), -bounds.top());
	}
	
}


