package isr;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Sprites
{
	public static Image shipBackground;
	public static Image rooms[][]; // [type][layer]
	
	public static void load() throws SlickException
	{
		String dir = "src/assets/";
		
		shipBackground = new Image(dir + "preview_salle.jpg");
		
		rooms = new Image[Room.TYPE_COUNT][Room.LAYER_COUNT];
		rooms[Room.TYPE_CORRIDOR][Room.LAYER_FLOOR] = new Image(dir + "");
		
		// TODO load sprites here
	}

}
