package isr;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.StateBasedGame;

/**
 * This is the submarine.
 * @author Marc
 *
 */
public class Ship
{
	private static final int ROOM_COUNT = 6;

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
	
	public Character[] getCharacters()
	{
		return characters;
	}

	public void render(GameContainer gc, StateBasedGame game, Graphics gfx)
	{
		// First, draw the ship (the background is NOT the Ship)
		// TODO draw Ship background
		
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



