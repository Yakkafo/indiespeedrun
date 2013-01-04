package backend;

import org.newdawn.slick.Input;

public class KeySequenceDetector
{
	public static final int[] KONAMI_CODE = 
	{
		Input.KEY_UP,
		Input.KEY_UP,
		Input.KEY_DOWN,
		Input.KEY_DOWN,
		Input.KEY_LEFT,
		Input.KEY_RIGHT,
		Input.KEY_LEFT,
		Input.KEY_RIGHT,
		Input.KEY_B,
		Input.KEY_A
	};
	
	public static final int[] KONAMI_CODE_ZQSD = 
	{
		Input.KEY_Z,
		Input.KEY_Z,
		Input.KEY_S,
		Input.KEY_S,
		Input.KEY_Q,
		Input.KEY_Q,
		Input.KEY_D,
		Input.KEY_D,
		Input.KEY_B,
		Input.KEY_A
	};

	private int model[];
	private int index;
	
	public KeySequenceDetector(int seq[])
	{
		model = seq;
	}
	
	public boolean keyPressMatch(int code)
	{
		if(model[index] == code)
		{
			index++;
			if(index == model.length)
			{
				index = 0;
				return true;
			}
			return false;
		}
		index = 0;
		return false;
	}

}


