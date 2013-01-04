package backend.ui;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

/**
 * Classic button with a text.
 * @author Marc
 *
 */
public class PushButton extends Button
{
	private static final int DEFAULT_HEIGHT = 16;
	private static final int DEFAULT_WIDTH = 128;

	private String text;

	public PushButton(Widget parent, int x, int y, int w, int h, String text)
	{
		super(parent, x, y, w, h);
		this.text = text;
	}
	
	public void setText(String text)
	{
		this.text = text;
	}

	public PushButton(Widget parent, int x, int y, int w, String text)
	{
		super(parent, x, y, w, DEFAULT_HEIGHT);
		this.text = text;
	}

	public PushButton(Widget parent, int x, int y, String text)
	{
		this(parent, x, y, DEFAULT_WIDTH, text);
	}
		
	public PushButton(Widget parent, String text)
	{
		this(parent, 0, 0, text);
	}
	
	public String getText()
	{
		return text;
	}
	
	@Override
	protected void onPress()
	{
		UIRenderer.getTheme().playButtonPressSound();
	}
	
	@Override
	public boolean mouseMoved(int oldX, int oldY, int newX, int newY)
	{
		if(!isMouseOver() && contains(newX, newY))
			UIRenderer.getTheme().playButtonHoverSound();
		return super.mouseMoved(oldX, oldY, newX, newY);
	}

	@Override
	protected void onRelease()
	{
		if(!isMouseOver())
			return;
		onAction();
	}

	@Override
	public void render(GameContainer gc, Graphics gfx)
	{
		UIRenderer.getTheme().renderPushButton(gfx, this);
	}

}



