package isr;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;

public class Sounds
{
	// Music
	private static Music music;
	
	public static void load() throws SlickException
	{
		String dir = Game.ASSETS_DIR;
		
		music = new Music(dir + "music.ogg", true);
		
		// TODO load sounds here
	}

}

