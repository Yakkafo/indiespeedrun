package isr;


import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import backend.ui.Label;
import backend.ui.Widget;
import backend.ui.WidgetContainer;

public class DescriptionBar extends WidgetContainer {

	private static Image voidAvatar;
	
	private Image img;
	private Label report; // This is a text summary, not THE report
	private Image avatar;

	public static void loadContent() throws SlickException
	{
		voidAvatar = new Image(Game.ASSETS_DIR + "chars/void.png");
	}
	
	public DescriptionBar(Widget parent, int x, int y, int width, int height) {
		super(parent, x, y, width, height);
		report = new Label(this, 225, 60, 500, 75, "");
		report.setTextColor(Color.white);
		this.add(report);
		try {
			img = new Image(Game.ASSETS_DIR+"layout.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		avatar = voidAvatar;
	}
	
	public void setAvatar(Image avatar)
	{
		if(avatar != null)
			this.avatar = avatar;
		else
			this.avatar = voidAvatar;
	}
	
	@Override
	public boolean isOpaqueContainer()
	{
		return true;
	}

	@Override
	public void render(GameContainer gc, Graphics gfx) {
		gfx.pushTransform();
		gfx.translate(getX() + 58, getY() + 10);
		gfx.drawImage(avatar, 
				0, 0, avatar.getWidth() + 8, avatar.getHeight() + 4, 
				0, 0, avatar.getWidth(), avatar.getHeight());
		gfx.popTransform();
		
		img.draw(getX(), getY());
		super.render(gc, gfx);
		
	}
	
	public void setText(String s)
	{
		report.setText(s);
	}

}
