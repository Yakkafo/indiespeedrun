package isr;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import backend.MathHelper;

public class ScrollBackground
{
	private static final float DEFAULT_SPEED = 10.f; // In px/s
	
	private static Image img;
	private static ScrollBackground instance;
	
	private float x;
	private float speed;
	
	public static void loadContent() throws SlickException
	{
		img = new Image(Game.ASSETS_DIR + "fond.png");
	}
	
	public static ScrollBackground get()
	{
		if(instance == null)
			instance = new ScrollBackground();
		return instance;
	}
	
	private ScrollBackground()
	{
		speed = DEFAULT_SPEED;
	}
	
	public void setSpeed(float pixelsPerSecond)
	{
		speed = pixelsPerSecond;
	}
	
	public void update(int delta)
	{
		x += speed * (float)delta / 1000.f;
	}

	public void render(Graphics gfx)
	{
		float w = img.getWidth();
		float x0 = -w * MathHelper.frac(x / w);
		gfx.drawImage(img, x0, 0, Color.gray);
		gfx.drawImage(img, x0 + img.getWidth(), 0, Color.gray);
		gfx.drawImage(img, x0 + 2.f*img.getWidth(), 0, Color.gray);
	}

}

