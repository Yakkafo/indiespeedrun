package isr;

import java.util.HashMap;
import java.util.HashSet;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

public class Room
{
	// Kinds of room
	public static final int CORRIDOR = 0;
	public static final int ENGINE = 1;
	public static final int JAIL = 2;
	public static final int SPY = 3;
	public static final int SLEEP = 4;
	public static final int MAIN = 5;
	
	// Drawing layers
	public static final int LAYER_FLOOR = 0;
	public static final int LAYER_OBJECTS = 1;
	public static final int LAYER_CHARACTERS = 2;
	public static final int LAYER_CEILING = 3;
	
	/** Characters in the room **/
	private HashSet<Character> characterRefs = new HashSet<Character>();
	
	/** Type of the room (corridor, engine, jail...) **/
	private int type;
	
	public Room(int roomType)
	{
		this.type = roomType;
	}
	
	public void init()
	{
		// TODO Load the room layout from its type
	}
	
	public void render(GameContainer gc, StateBasedGame game, Graphics gfx, int layer)
	{
		// TODO draw the room depending which layer is specified
	}

	public int getType()
	{
		return type;
	}
	
	public void addCharacter(Character c)
	{
		if(isCharacterIn(c))
		{
			System.out.println(
				"ERROR: cannot add a character to a room where it is already present");
			return;
		}
		characterRefs.add(c);
	}
	
	public void removeCharacter(Character c)
	{
		characterRefs.remove(c);
	}
	
	public boolean isCharacterIn(Character c)
	{
		return characterRefs.contains(c);
	}

}





