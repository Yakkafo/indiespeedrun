package isr;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import backend.ui.BasicWidget;
import backend.ui.Widget;

public class ProgressBar extends BasicWidget {

	private Image imgBar;
	private Image imgShip;
	private Image imgEnemy;
	private float xShip;
	private int goal;
	private float enemy_progression;
	
	public ProgressBar(Widget parent, int x, int y, int width, int height, int goal, int enemy_derparture) {
		super(parent, x, y, width, height);
		
		xShip = 0;
		this.enemy_progression = -560;
		this.goal = goal;
		
		try {
			imgBar = new Image(Game.ASSETS_DIR+"progress_bar.png");
			imgShip = new Image(Game.ASSETS_DIR+"mini_ship.png");
			imgEnemy = new Image(Game.ASSETS_DIR+"enemi_mini.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void render(GameContainer gc, Graphics gfx) {
		imgBar.draw(getX(), getY());
		imgShip.draw(getX()+xShip, getY()-18);
		if(enemy_progression >= 0)
			imgEnemy.draw(getX()+enemy_progression, getY()-18);

	}

	public void setProgression(int progression) {
		xShip = convertMilesToPixels(progression);
		System.out.println(xShip);
	}
	
	public void makeEnemyMove(int speed)
	{
		enemy_progression += convertMilesToPixels(speed);
	}
	
	private float convertMilesToPixels(int miles)
	{
		return (float)miles*(float)getWidth()/(float)goal;
	}
	
	/**Check if the game is lost*/
	public boolean isLost()
	{
		return enemy_progression >= xShip;
	}

}
