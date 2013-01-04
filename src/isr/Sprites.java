package isr;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Sprites
{
	public static Image shipBackground;
	public static Image shipWalls;
	public static Image roomFloors[]; // [type]
	public static Image roomObjects[]; // [type]
	public static Image spyCharacter;
	
	public static void load() throws SlickException
	{
		String dir = Game.ASSETS_DIR;

		shipBackground = new Image(dir + "preview_salle.jpg");
		//shipWalls = new Image(dir + "architecture.png");

		// Floors
		roomFloors = new Image[Room.TYPE_COUNT];
		//roomFloors[Room.TYPE_CORRIDOR] = new Image(dir + "corridor-floor.png");
		//...

		// Objects & decorations
		roomObjects = new Image[Room.LAYER_COUNT];
		//roomObjects[Room.TYPE_CORRIDOR] = new Image(dir + "corridor-objects.png");
		//...s
		
		// Spy
		//...
	}

}



