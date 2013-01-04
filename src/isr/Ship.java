package isr;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 * This is the submarine.
 * @author Marc
 *
 */
public class Ship
{
	private static final int ROOM_COUNT = 6;
	
	private static Image backgroundImage;
	private static Image wallsImage;

	private static Ship instance;

	private Room rooms[] = new Room[ROOM_COUNT];
	private Character characters[];
	private static Image background;
	
	public static Ship get()
	{
		if(instance == null)
			instance = new Ship();
		return instance;
	}
	
	public static void loadContent() throws SlickException
	{
		backgroundImage = new Image(Game.ASSETS_DIR + "sousmarin.png");
	}

	public Ship()
	{
	}
		
	public void init()
	{
		// Rooms creations
		setRoom(new Room(Room.TYPE_CORRIDOR));
		setRoom(new Room(Room.TYPE_ENGINE));
		setRoom(new Room(Room.TYPE_JAIL));
		setRoom(new Room(Room.TYPE_SPY));
		setRoom(new Room(Room.TYPE_SLEEP));
		setRoom(new Room(Room.TYPE_MAIN));
		
		for(Room r : rooms)
			r.init();
		
		// Characters creation
		characters = Character.createCrew();
		
	}
	
	private void setRoom(Room room) // Just a shorthand for creating rooms
	{
		rooms[room.getType()] = room;
	}
	
	public Room getRoom(int ID)
	{
		return rooms[ID];
	}
	
	public Character getCharacter(int ID)
	{
		return characters[ID];
	}
	
	/**
	 * Searches the first room that is under the
	 * specified screen position (as the mouse cursor)
	 * and returns it if there is something.
	 * @param screenX
	 * @param screenY
	 * @return the room, or null if there is nothing
	 */
	public Room getRoomAt(int screenX, int screenY)
	{
		for(Room r : rooms)
		{
			if(r.contains(screenX, screenY))
				return r;
		}
		return null;
	}
	
	/**
	 * Searches the first character that is under the
	 * specified screen position (as the mouse cursor)
	 * and returns it if there is something.
	 * @param screenX
	 * @param screenY
	 * @return the character, or null if there is nothing
	 */
	public Character getCharacterAt(int screenX, int screenY)
	{
		for(Character c : characters)
		{
			if(c.contains(screenX, screenY))
				return c;
		}
		return null;
	}

	public void render(GameContainer gc, StateBasedGame game, Graphics gfx)
	{
		// First, draw the ship (the background is NOT the Ship)
		final Image img = backgroundImage;
		gfx.drawImage(img, 
				(img.getWidth() - gc.getWidth() / 2),
				(img.getHeight() - gc.getHeight() / 2));
		
		// Grounds		
		for(Room r : rooms)
			r.render(gc, game, gfx, Room.LAYER_FLOOR);
		
		// Objects
		for(Room r : rooms)
			r.render(gc, game, gfx, Room.LAYER_OBJECTS);
		
		// Characters
//		for(Character c : characters)
//			c.render(gc, game, gfx); // TODO render method in Character
		
		// Ceiling
		for(Room r : rooms)
			r.render(gc, game, gfx, Room.LAYER_CEILING);
	}

}



