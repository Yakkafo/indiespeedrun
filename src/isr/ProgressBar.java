package isr;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import backend.ui.BasicWidget;
import backend.ui.Widget;

public class ProgressBar extends BasicWidget {
	
	Image imgBar;
	Image imgShip;
	int xShip;
	
	public ProgressBar(Widget parent, int x, int y, int width, int height) {
		super(parent, x, y, width, height);
		
		xShip = 0;
		
		try {
			imgBar = new Image("assets/progress_bar.png");
			imgShip = new Image("assets/mini_ship.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void incrementProgression(int deltaX)
	{
		xShip += deltaX;
	}
	
	@Override
	public void render(GameContainer gc, Graphics gfx) {
		imgBar.draw(getX(), getY());
		imgShip.draw(getX()+xShip, getY()-18);

	}

}
