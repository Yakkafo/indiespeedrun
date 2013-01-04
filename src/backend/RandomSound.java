package backend;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Sound;

public class RandomSound
{
	private float minPitch;
	private float maxPitch;
	private List<Sound> sounds = new ArrayList<Sound>();
	
	public RandomSound()
	{
		minPitch = 0.9f;
		maxPitch = 1.1f;
	}
	
	public RandomSound(float minPitch, float maxPitch)
	{
		this.minPitch = minPitch;
		this.maxPitch = maxPitch;
	}
	
	public RandomSound addVariationSound(Sound snd)
	{
		sounds.add(snd);
		return this;
	}
	
	public void play()
	{
		play(1);
	}
	
	public void play(float volume)
	{
		if(sounds.size() == 0)
			return;
		int i = MathHelper.randInt(0, sounds.size() - 1);
		Sound sound = sounds.get(i);
		float pitch = MathHelper.randFloat(minPitch, maxPitch);
		sound.play(pitch, volume);
	}
	
	public void playAt(float x, float y)
	{
		playAt(x, y, 1);
	}
	
	public void playAt(float x, float y, float volume)
	{
		if(sounds.size() == 0)
			return;		
		Sound sound = sounds.get(MathHelper.randInt(0, sounds.size() - 1));
		float pitch = MathHelper.randFloat(minPitch, maxPitch);
		sound.playAt(pitch, 1, x, y, 0);
	}
}

