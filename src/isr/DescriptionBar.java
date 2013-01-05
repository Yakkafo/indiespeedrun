package isr;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import backend.ui.BasicWidget;
import backend.ui.Widget;

public class DescriptionBar extends BasicWidget {

	Image img;
	
	public DescriptionBar(Widget parent, int x, int y, int width, int height) {
		super(parent, x, y, width, height);
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
	}

}
