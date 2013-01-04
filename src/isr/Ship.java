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
	
	public static Ship get()
	{
		if(instance == null)
			instance = new Ship();
		return instance;
	}
	
	public Ship()
	{
		rooms[Room.CORRIDOR] = new Room();
		rooms[Room.ENGINE] = new Room();
		rooms[Room.JAIL] = new Room();
		rooms[Room.SPY] = new Room();
		rooms[Room.SLEEP] = new Room();
		rooms[Room.JAIL] = new Room();
	}

	public void render(GameContainer gc, StateBasedGame game, Graphics gfx)
	{
		for(Room r : rooms)
			r.render(gc, game, gfx);
	}

}



