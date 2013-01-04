package isr;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
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
	
	public static Ship get()
	{
		if(instance == null)
			instance = new Ship();
		return instance;
	}
	
	public Ship()
	{
		// Rooms creations
		setRoom(new Room(Room.CORRIDOR));
		setRoom(new Room(Room.ENGINE));
		setRoom(new Room(Room.JAIL));
		setRoom(new Room(Room.SPY));
		setRoom(new Room(Room.SLEEP));
		setRoom(new Room(Room.MAIN));
		
		// Characters creation
		characters = Character.createCrew(); // TODO uncomment when available
	}
	
	private void setRoom(Room room)
	{
		rooms[room.getType()] = room;
	}

	public void render(GameContainer gc, StateBasedGame game, Graphics gfx)
	{
		
	}

}



