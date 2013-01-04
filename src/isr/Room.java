package isr;

import java.util.HashMap;
import java.util.HashSet;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.StateBasedGame;

import backend.geom.Vector2i;

public class Room
{
	// Kinds of room
	public static final int TYPE_CORRIDOR = 0;
	public static final int TYPE_ENGINE = 1;
	public static final int TYPE_JAIL = 2;
	public static final int TYPE_SPY = 3;
	public static final int TYPE_SLEEP = 4;
	public static final int TYPE_MAIN = 5;
	public static final int TYPE_COUNT = 6;
	
	// Drawing layers
	public static final int LAYER_FLOOR = 0;
	public static final int LAYER_OBJECTS = 1;
	public static final int LAYER_CHARACTERS = 2;
	public static final int LAYER_CEILING = 3;
	public static final int LAYER_COUNT = 4;
	
	/** Characters in the room **/
	private HashSet<Character> characterRefs = new HashSet<Character>();
	
	/** Type of the room (corridor, engine, jail...) **/
	private int type;
	
	/** Position of the top-left-corner of the room's bounding rectangle **/
	private Vector2i pos = new Vector2i();
	
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
	
	/**
	 * check if the point is in the room
	 * @param bx : global x in pixels
	 * @param by : global y in pixels
	 * @return
	 */
	public boolean contains(int bx, int by)
	{
		int spriteX = bx - pos.x;
		int spriteY = by - pos.y;		
		// We use the floor as a collision mask because it covers all the surface of the room
		Image img = Sprites.roomFloors[type]; 
		// If in the image
		if(spriteX >= 0 && spriteY >= 0 && spriteX < img.getWidth() && spriteY < img.getHeight())
		{
			// If the pixel is not (roughly) transparent
			return img.getColor(spriteX, spriteY).a > 0.01f;
		}
		return false;
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





