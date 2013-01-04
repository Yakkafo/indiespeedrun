package isr;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import backend.ui.UIBasicGameState;

public class GamePlay extends UIBasicGameState
{
	private static Color bgColor = new Color(0, 64, 128);
	
	private Character selectedCharacter;

	@Override
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException
	{
		// TODO Auto-generated method stub

	}
	
	@Override
	public void enter(GameContainer container, StateBasedGame game)
			throws SlickException
	{
		super.enter(container, game);
		
		Ship.get().init();
	}

	@Override
	protected void createUI(GameContainer container, StateBasedGame game)
			throws SlickException
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void render(GameContainer gc, StateBasedGame game, Graphics gfx)
			throws SlickException
	{
		gfx.setBackground(bgColor);
		
		Ship.get().render(gc, game, gfx);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame game, int delta)
			throws SlickException
	{
		
	}

	@Override
	public int getID()
	{
		return Game.GAME_PLAY;
	}
	
	@Override
	public void mousePressed(int button, int x, int y) 
	{
		if(button == Input.MOUSE_LEFT_BUTTON) //Characters selection
		{
			Character c = Ship.get().getCharacterAt(x, y);
			if(c != null) // Character under the mouse !
			{
				c.setSelected(true);
				selectedCharacter = c;
			}
			else
			{
				// Unselect the last selected character
				if(selectedCharacter != null)
				{
					selectedCharacter.setSelected(false);
					selectedCharacter = null;
				}
			}
		}
		else if(button == Input.MOUSE_RIGHT_BUTTON)	// Send action to a character
		{
			if(selectedCharacter != null) // If we have selected a character
			{
				Room r = Ship.get().getRoomAt(x, y); // Get the room from click position
				if(r != null)
				{
					// If room found, send the character to it
					selectedCharacter.setNextAction(r.getType());
				}
			}
		}
	}

}


