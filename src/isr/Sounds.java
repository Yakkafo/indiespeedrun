package isr;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;

public class Sounds
{
	// Music
	public static Music music1;
	public static Music music2;
	
	public static void load() throws SlickException
	{
		String dir = Game.ASSETS_DIR;
		
		music1 = new Music(dir + "music1.ogg", true);
		music2 = new Music(dir + "music2.ogg", true);
		
		// TODO load sounds here
	}

}

