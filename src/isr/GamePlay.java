package isr;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import backend.geom.Vector2i;
import backend.ui.UIBasicGameState;

public class GamePlay extends UIBasicGameState
{
	private static Color bgColor = new Color(0, 64, 128);
	
	private Character selectedCharacter;
	private Vector2i viewOffset = new Vector2i();

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
		
		Vector2i shipSize = Ship.get().getBackgroundSize();
		viewOffset.x = (container.getWidth() - shipSize.x) / 2;
		viewOffset.y = (container.getHeight() - shipSize.y) / 2;
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
		
		gfx.pushTransform();
		gfx.translate(viewOffset.x, viewOffset.y);
		
		Ship.get().render(gc, game, gfx);
		
		gfx.popTransform();
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
	
	private Vector2i convertToSceneCoords(int x, int y)
	{
		return new Vector2i(x - viewOffset.x, y - viewOffset.y);
	}
	
	@Override
	public void mousePressed(int button, int x, int y) 
	{
		Vector2i pos = convertToSceneCoords(x, y);
		
		if(button == Input.MOUSE_LEFT_BUTTON) //Characters selection
		{
			Character c = Ship.get().getCharacterAt(pos.x, pos.y);
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
				Room r = Ship.get().getRoomAt(pos.x, pos.y); // Get the room from click position
				if(r != null)
				{
					// If room found, send the character to it
					selectedCharacter.setNextAction(r.getType());
				}
			}
		}
	}

}


