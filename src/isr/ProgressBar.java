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
	float xShip;
	int goal;
	
	public ProgressBar(Widget parent, int x, int y, int width, int height, int goal) {
		super(parent, x, y, width, height);
		
		xShip = 0;
		this.goal = goal;
		
		try {
			imgBar = new Image("assets/progress_bar.png");
			imgShip = new Image("assets/mini_ship.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void render(GameContainer gc, Graphics gfx) {
		imgBar.draw(getX(), getY());
		imgShip.draw(getX()+xShip, getY()-18);

	}

	public void setProgression(int progression) {
		xShip = (float)progression*(float)getWidth()/(float)goal;	
		System.out.println(xShip);
	}

}
