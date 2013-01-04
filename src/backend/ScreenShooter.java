package backend;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.imageout.ImageOut;

/**
 * A simple utility class to take screenshots using Slick2D
 * @author Marc
 *
 */
public class ScreenShooter
{
	private int shotNum;
	
	public void shoot(Graphics gfx, GameContainer gc) throws SlickException
	{
		shotNum++;

		Image screen = new Image(gc.getWidth(), gc.getHeight());
		gfx.copyArea(screen, 0, 0);
		
//		Date date = new Date();
		
		String filename = "screenshot-" + shotNum + ".png";
		
		ImageOut.write(screen, filename, false);
		screen.destroy();
	}
	
}

