package isr;

public class Character
{
	public static final int NB_CHARACTERS = 6;
	private static final int INITIAL_LOYALTY[] = {40, 50, 60, 70, 80, 90};
	/**The character is loyal if his loyalty > this*/
	private static final int LOYAL_STEP = 25; 
	/**Turns before the character is sleepy*/
	private static final int TIME_SLEEP = 3; 
	
	private int id;
	/**Current percentage of loyalty*/
	private int loyalty; 
	/**Time (in turns) before the last sleep.*/
	private int lastSleep; 
	
	public Character(int id)
	{
		if(id < NB_CHARACTERS && id > 0)
		{
			this.id = id;
			this.loyalty = INITIAL_LOYALTY[id-1];
			this.lastSleep = 0;			
		}
		else
			System.out.println("Error: wrong ID!");
	}
	
	/**
	 * Check if the character is loyal.
	 * @return true if the character is loyal.
	 */
	public boolean isLoyal()
	{
		return loyalty > LOYAL_STEP;
	}
	
	/**
	 * Check if the character is tired.
	 * @return true if the character is tired.
	 */
	public boolean isTired()
	{
		return lastSleep >= TIME_SLEEP;
	}
	
}
