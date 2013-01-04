package isr;

/**
 * This is the submarine.
 * @author Marc
 *
 */
public class Ship
{
	private static Ship instance;
	
	public static Ship get()
	{
		if(instance == null)
			instance = new Ship();
		return instance;
	}

}
