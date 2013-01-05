package isr;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Spy {
	
	private Image img;
	
	public Spy()
	{
		try {
			img = new Image("assets/espion.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void render(GameContainer gc, StateBasedGame game, Graphics gfx)
	{
		img.draw(575, 250);
	}

}
