package isr;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import backend.ui.PushButton;
import backend.ui.Widget;

public class MenuButton extends PushButton
{
	private static final Color NORMAL_COLOR = new Color(255,255,255);
	private static final Color HOVER_COLOR = new Color(255,255,0);
	
	private Image img;
	
	public MenuButton(Widget parent, int x, int y, Image image)
	{
		super(parent, x, y, image.getWidth(), image.getHeight(), "");
		img = image;
		setAlign(Widget.ALIGN_CENTER, Widget.ALIGN_NONE, 0, 0);
	}
	
	@Override
	public void render(GameContainer gc, Graphics gfx)
	{
		Color clr = NORMAL_COLOR;
		if(isMouseOver())
			clr = HOVER_COLOR;
		gfx.drawImage(img, getAbsoluteX(), getAbsoluteY(), clr);
	}
	
}
