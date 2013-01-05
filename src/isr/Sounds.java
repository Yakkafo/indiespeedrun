package isr;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;

public class Sounds
{
	// Music
	public static Music music;
	public static Music kMusic;
	
	public static void load() throws SlickException
	{
		String dir = Game.ASSETS_DIR;
		
		music = new Music(dir + "music.ogg", true);
		kMusic = new Music(dir + "kmusic.ogg", true);
		
		// TODO load sounds here
	}

}

