package isr;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import backend.geom.Vector2i;

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

	
	private Spy spy;
	
	public static Ship get()
	{
		if(instance == null)
			instance = new Ship();
		return instance;
	}
	
	public static void loadContent() throws SlickException
	{
		backgroundImage = new Image(Game.ASSETS_DIR + "sousmarin.png");
		wallsImage = new Image(Game.ASSETS_DIR + "archi.png");
	}

	public Ship()
	{
		spy = new Spy();
	}
	
	public void init()
	{
		// Rooms creations
		setRoom(new Room(RoomType.CELL));
		setRoom(new Room(RoomType.COMMON));
		setRoom(new Room(RoomType.CORRIDOR));
		setRoom(new Room(RoomType.DORM));
		setRoom(new Room(RoomType.ENGINE));
		setRoom(new Room(RoomType.HOLD));
		
		for(Room r : rooms)
			r.init();
		
		// Characters creation
		characters = Character.createCrew();
		
	}
	
	public Vector2i getBackgroundSize()
	{
		return new Vector2i(backgroundImage.getWidth(), backgroundImage.getHeight());
	}
	
	private void setRoom(Room room) // Just a shorthand for creating rooms
	{
		rooms[room.getType().ordinal()] = room;
	}
	
	public Spy getSpy()
	{
		return spy;
	}
	
	public Room getRoom(int ID)
	{
		return rooms[ID];
	}
	
	public Character getCharacter(int ID)
	{
		return characters[ID];
	}
	
	public int getCharactersSize()
	{
		return characters.length;
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
		Color clr = Color.white;
		if(Sounds.kMusic.playing())
			clr = Color.yellow;
		gfx.drawImage(img, 0, 0, clr);
		
		if(game.getCurrentStateID() != Game.INTRO_DIVE)
		{
			// Grounds
			for(Room r : rooms)
				r.render(gc, game, gfx, Room.LAYER_FLOOR);
			
	//		// Objects
	//		for(Room r : rooms)
	//			r.render(gc, game, gfx, Room.LAYER_OBJECTS);
			
			//spy
			spy.render(gc, game, gfx);
			
			// Characters
			for(Character c : characters)
				c.render(gc, game, gfx); // TODO render method in Character
			
			// Ceiling
			gfx.drawImage(wallsImage, 243, 218);
		}

	}
	
	void turn(Report report)
	{
//		for(Character c : characters)
//			c.doMovePhase();
		for(Character c : characters)
			c.doResolvePhase(report);
	}

}



