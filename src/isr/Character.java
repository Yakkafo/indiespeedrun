package isr;

import backend.MathHelper;

public class Character
{
	public static final int NB_CHARACTERS = 6;
	private static final int INITIAL_LOYALTY[] = {40, 50, 60, 70, 80, 90};
	/**Possible names for characters*/
	private static final String NAMES[] = {"Sarah", "Ellen", "Lola", "Brutus", "Egdar", "John"};
	/**The character is loyal if his loyalty > this*/
	private static final int LOYAL_STEP = 25; 
	/**Turns before the character becomes sleepy*/
	private static final int TIME_SLEEP = 3; 
	/** Probabilty for a sabotage */
	private static final float SABOTAGE_PROBABILITY = 0.33f;
	
	/**id of the character. id is unique for each character and is between 0 & 5*/
	private int id;
	/**Each character has his own name*/
	private String name;
	/**Current percentage of loyalty*/
	private int loyalty; 
	/**Time (in turns) before the last sleep.*/
	private int lastSleep; 
	/**Next action of the character. -1 means that there is not any actions yet*/
	private int nextAction;
	/**Where the character is*/
	private int currentRoom;
	
	public Character(int id, int loyalty)
	{
		if(id < NB_CHARACTERS && id >= 0)
		{
			this.id = id;
			this.loyalty = loyalty;
			this.lastSleep = 0;
			this.nextAction = -1;
			this.name = NAMES[id];
			this.currentRoom = 0;
		}
		else
			System.out.println("Error: wrong ID!");
	}
	
	public int getNextAction()
	{
		return nextAction;
	}
	
	public int getLoyalty()
	{
		return loyalty;
	}
	
	public String getName()
	{
		return name;
	}
	
	public int getId()
	{
		return id;
	}
	
	public int getCurrentRoom()
	{
		return currentRoom;
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
	
	/**
	 * Initialize a crew for a ship.
	 * @return a Character[] where all the crew is initialized
	 */
	public static Character[] createCrew()
	{
		Character crew[] = new Character[NB_CHARACTERS];
		int temp_loyalties[] = INITIAL_LOYALTY;
		
		for(int i = 0; i < NB_CHARACTERS; i++)
		{
			int r = MathHelper.randInt(0, NB_CHARACTERS-1);
			int secure = 0; //security against infinite loop
			while(temp_loyalties[r] == -1)
			{
				r++;
				secure ++;
				if(r >= NB_CHARACTERS)
					r = 0;
				if(secure >= NB_CHARACTERS)
				{
					System.out.println("Error: wrong initial loyalty.");
					break;
				}
			}
			crew[i] = new Character(i, temp_loyalties[r]);
			temp_loyalties[r] = -1;
		}
		
		return crew;
	}
	
	public String toString()
	{
		String s = new String();
		s += "Name: "+name+"\n";
		s += "Loyalty: "+loyalty+"\n";
		return s;
	}
	
	
}
