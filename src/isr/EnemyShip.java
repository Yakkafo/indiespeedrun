package isr;

class EnemyShip
{
	public static final int DEPARTURE = -200;
	public static final int BASE_SPEED_PER_TURN = 40;
	
	private static EnemyShip instance;
	
	public static EnemyShip get()
	{
		if(instance == null)
			instance = new EnemyShip();
		return instance;
	}
	
	private int progress;
	
	private EnemyShip()
	{
		progress = DEPARTURE;
	}
	
	public int getProgressMiles()
	{
		return progress;
	}
	
	public float getProgressRatio()
	{
		return (float)progress / (float)Ship.TRIP_DISTANCE;
	}
	
	public boolean reachedPlayer()
	{
		return progress >= Ship.get().getProgressMiles();
	}
	
	public void advance()
	{
		advance(BASE_SPEED_PER_TURN);
	}

	public void advance(int distanceMiles)
	{
		progress += distanceMiles;
		// The enemy cannot overtake the player's ship
		if(progress > Ship.get().getProgressMiles())
			progress = Ship.get().getProgressMiles();
	}
	
}

