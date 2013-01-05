package isr;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import backend.ui.PushButton;
import backend.ui.Widget;

public class NextTurnButton extends PushButton
{
	private static SpriteSheet sprites;
	
	public NextTurnButton(Widget parent, int x, int y)
	{
		super(parent, x, y, 150, 75, "");
		
		if(sprites == null)
		{
			try {
				sprites = new SpriteSheet("assets/next_turn_button.png", 150, 75);
			} catch (SlickException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void render(GameContainer gc, Graphics gfx)
	{
		gfx.drawImage(sprites.getSprite(isPressed() ? 1 : 0, 0), 
				getAbsoluteX(), getAbsoluteY());
	}
	
}
