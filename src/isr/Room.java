package isr;

import java.util.HashMap;
import java.util.HashSet;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.Log;

import backend.geom.Vector2i;

public class Room
{
	// Drawing layers
	public static final int LAYER_FLOOR = 0;
	public static final int LAYER_OBJECTS = 1;
	public static final int LAYER_CHARACTERS = 2;
	public static final int LAYER_CEILING = 3;
	public static final int LAYER_COUNT = 4;
	
	// Sprites
	private static Image backgrounds[];
	
	/** Positions a Character can occuppy **/
	private Slot[] slots;
	/** Count of free slots (for performance) **/
	private int freeSlots;
	
	/** Type of the room (corridor, engine, jail...) **/
	private int type;
	
	/** Position of the top-left-corner of the room's bounding rectangle **/
	private Vector2i pos = new Vector2i();
	
	public static void loadContent() throws SlickException
	{
		backgrounds = new Image[RoomType.values().length];
		for(RoomType t : RoomType.values())
			backgrounds[t.ordinal()] = new Image(Game.ASSETS_DIR + t.spriteName);
	}
	
	public Room(RoomType type)
	{
		this.type = type.ordinal();
		if(type.size() > 0)
		{
			pos.set(type.x, type.y);
			slots = new Slot[type.size()];
			freeSlots = slots.length;
			for(int i = 0; i < slots.length; i++)
			{
				slots[i] = new Slot();
				slots[i].pos = new Vector2i(type.charactersPositions[i]);
			}
		}
	}
	
	public void init()
	{
		// TODO Load the room layout from its type
	}
	
	public void render(GameContainer gc, StateBasedGame game, Graphics gfx, int layer)
	{
		gfx.drawImage(backgrounds[type], 
				RoomType.values()[type].x, 
				RoomType.values()[type].y);
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
		Image img = backgrounds[type];
		// If in the image
		if(spriteX >= 0 && spriteY >= 0 && spriteX < img.getWidth() && spriteY < img.getHeight())
		{
			// If the pixel is not (roughly) transparent
			return img.getColor(spriteX, spriteY).a > 0.01f;
		}
		return false;
	}
	
	public boolean isFull()
	{
		return freeSlots == 0 || slots == null || slots.length == 0;
	}

	/**
	 * Adds a character to the room by finding a free slot.
	 * Returns the position of the character if there is room for him, 
	 * or null if not.
	 * Warning: this method doesn't guaranties that a character will not 
	 * be in two rooms at the same time.
	 * @param c
	 * @return
	 */
	public Vector2i addCharacter(Character c)
	{
		String roomName = RoomType.values()[type].name;
		if(isFull())
		{
			System.out.println(
				"INFO: the room is full (" + roomName + ")");
			return null;
		}
		if(isCharacterIn(c))
		{
			System.out.println(
				"ERROR: cannot add a character to a room " +
				"where it is already present (" + roomName + ")");
			return null;
		}
		for(Slot s : slots)
		{
			if(s.characterRef == null)
			{
				s.characterRef = c;
				freeSlots--;
				return s.pos;
			}
		}
		return null;
	}
	
	public void removeCharacter(Character c)
	{
		for(Slot s : slots)
		{
			if(s.characterRef == c)
			{
				s.characterRef = null;
				freeSlots++;
				return;
			}
		}
		Log.warn("tried to remove a character from a room, but it was not here.");
	}
	
	public boolean isCharacterIn(Character c)
	{
		for(Slot s : slots)
		{
			if(s.characterRef == c)
				return true;
		}
		return false;
	}

	/**
	 * This is a position a character can occupy in the room.
	 * @author Marc
	 *
	 */
	private class Slot
	{
		public Vector2i pos;
		public Character characterRef;
	}

}





