package isr;


import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import backend.ui.Label;
import backend.ui.Panel;
import backend.ui.Widget;

public class DescriptionBar extends Panel {

	private Image img;
	private Label report;
	
	public DescriptionBar(Widget parent, int x, int y, int width, int height) {
		super(parent, x, y, width, height);
		report = new Label(this, 225, 60, 500, 75, "");
		report.setTextColor(Color.white);
		this.add(report);
		try {
			img = new Image("assets/layout.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void render(GameContainer gc, Graphics gfx) {
		img.draw(getX(), getY());
		super.render(gc, gfx);
		
	}
	
	public void setText(String s)
	{
		report.setText(s);
	}

}
