package backend.ui;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class ProgressBar extends BasicWidget
{
	private static final int DEFAULT_HEIGHT = 8;
	
	private float progress; // between 0 and 1
	
	public ProgressBar(Widget parent, int x, int y, int width, int height)
	{
		super(parent, x, y, width, height);
		progress = 0;
	}
	
	public ProgressBar(Widget parent, int width)
	{
		this(parent, 0, 0, width, DEFAULT_HEIGHT);
	}
	
	/**
	 * Set progress from a [0, 100] integer
	 * @param p
	 */
	public void setProgressPercent(int p)
	{
		if(p > 100)
			p = 100;
		else if(p < 0)
			p = 0;
		progress = p / 100.f;
	}
	
	/**
	 * Sets progress from a [0, 1] floating number
	 * @param p
	 */
	public void setProgress(float p)
	{
		if(p < 0)
			progress = 0;
		else if(p > 1)
			progress = 1;
		else
			progress = p;
	}
	
	/**
	 * Get progress ratio
	 * @return ratio between 0 and 1
	 */
	public float getProgress()
	{
		return progress;
	}
	
	@Override
	public void render(GameContainer gc, Graphics gfx)
	{
		UIRenderer.getTheme().renderProgressBar(gfx, this);
	}

}



