package isr;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import backend.ui.BasicWidget;
import backend.ui.Widget;

public class ProgressBar extends BasicWidget 
{
	private Image imgBar;
	private Image imgShip;
	private Image imgEnemy;
	
	public ProgressBar(Widget parent, int x, int y, int width, int height) 
	{
		super(parent, x, y, width, height);
				
		try {
			imgBar = new Image(Game.ASSETS_DIR+"progress_bar.png");
			imgShip = new Image(Game.ASSETS_DIR+"mini_ship.png");
			imgEnemy = new Image(Game.ASSETS_DIR+"ennemi.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void render(GameContainer gc, Graphics gfx) 
	{
		gfx.pushTransform();
		gfx.translate(getAbsoluteX(), getAbsoluteY());
		
		float w = (float)getWidth();
		
		imgBar.draw();
		imgShip.draw(w * Ship.get().getProgressRatio(), -18);
		
		if(EnemyShip.get().getProgressMiles() >= 0)
			imgEnemy.draw(w * EnemyShip.get().getProgressRatio(), -18);
		
		gfx.popTransform();
	}

}



