package isr;

public class Character
{
	public static final int NB_CHARACTERS = 6;
	private static final int INITIAL_LOYALTY[] = {40, 50, 60, 70, 80, 90};
	private static final int LOYAL_STEP = 25; //The character is loyal if his loyalty > this
	
	private int id;
	private int loyalty; //Current percentage of loyalty
	
	
	
	public Character(int id)
	{
		if(id < NB_CHARACTERS && id > 0)
		{
			this.id = id;
			this.loyalty = INITIAL_LOYALTY[id-1];
			
		}
		else
			System.out.println("Error: wrong ID!");
	}
	
	/**
	 * Check if the character is loyal.
	 * @return true if the chracter is loyal.
	 */
	public boolean isLoyal()
	{
		return loyalty > LOYAL_STEP;
	}
	
}
