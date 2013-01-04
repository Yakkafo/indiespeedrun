package backend.audio;

import org.newdawn.slick.Music;
import org.newdawn.slick.MusicListener;

/**
 * Simple music manager that fades previous music before playing a new one.
 * @author Marc
 *
 */
public class MusicPlayer implements MusicListener
{
	private static MusicPlayer instance;
	
	private Music currentMusic;
	private Music nextMusic;
	private float nextVolume;
	
	public static MusicPlayer get()
	{
		if(instance == null)
			instance = new MusicPlayer();
		return instance;
	}
	
	private MusicPlayer()
	{
	}
	
	public void removeNext()
	{
		nextMusic = null;
	}
	
	public void stop()
	{
		if(currentMusic != null)
			currentMusic.stop();
	}
	
	public void fade(int duration)
	{
		if(currentMusic != null)
			currentMusic.fade(duration, 0, true);
	}
	
	public void loop(Music m, float volume)
	{
		play(m, volume, 0, true);
	}
	
	public void play(Music m, float volume)
	{
		play(m, volume, 0, false);
	}
	
	public void play(Music m, float volume, int previousFadeTime, boolean loop)
	{
		if(currentMusic != null && currentMusic.playing() && previousFadeTime > 0)
		{
			currentMusic.fade(previousFadeTime, 0, true);
			nextMusic = m;
			nextVolume = volume;
		}
		else
		{
			currentMusic = m;
			playCurrent(volume, loop);
		}
	}
	
	private void playCurrent(float volume, boolean loop)
	{
		if(!currentMusic.playing())
		{
			if(loop)
				currentMusic.loop(1, volume);
			else
				currentMusic.play(1, volume);
			currentMusic.addListener(this);
		}
	}

	@Override
	public void musicEnded(Music music)
	{
		if(music == currentMusic)
		{
			if(nextMusic != null)
			{
				currentMusic.stop();
				currentMusic = nextMusic;
				nextMusic = null;
				playCurrent(nextVolume, true);
			}
			else
				currentMusic = null;
		}
	}

	@Override
	public void musicSwapped(Music music, Music newMusic)
	{
	}

}


