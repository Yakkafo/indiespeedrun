package isr;


import backend.geom.Vector2i;

public enum RoomType 
{
	ENGINE ("Engine room", "engine.png", 253, 230),
	HOLD ("Hold room", "hold.png", 571, 230),
	DORM ("Dormitory", "dorm.png", 703, 230),
	CELL ("Cell", "cell.png", 571, 413),
	COMMON ("Common room", "commonroom.png", 703, 413),
	CORRIDOR ("Corridor", "corridor.png", 459, 344),
	COMMAND ("Command room", "command.png", 981, 249);
	
	//Each array contains character positions for each room in relative pixel coordinates
	static
	{
		Vector2i posEngine[] = {
				new Vector2i(250, 55), 
				new Vector2i(165, 145), 
				new Vector2i(250, 225)
		};
		ENGINE.charactersPositions = posEngine;
		
		Vector2i posHold[] = {new Vector2i(90, 70)};
		HOLD.charactersPositions = posHold;
		
		Vector2i posDormitory[] = {
				new Vector2i(70, 70), 
				new Vector2i(200, 70)
		};
		DORM.charactersPositions = posDormitory;
		
		Vector2i posCell[] = {new Vector2i(50, 50)};
		CELL.charactersPositions = posCell;
		
		Vector2i posCommon[] = {
				new Vector2i(130, 50), 
				new Vector2i(250, 50)
		};
		COMMON.charactersPositions = posCommon;
		
		Vector2i posCorridor[] = {
				new Vector2i(100, 30), 
				new Vector2i(150, 30), 
				new Vector2i(200, 30), 
				new Vector2i(250, 30), 
				new Vector2i(300, 30), 
				new Vector2i(350, 30)
		};
		CORRIDOR.charactersPositions = posCorridor;
		
		Vector2i posCommand[] = {};
		COMMAND.charactersPositions = posCommand;
	}
	
	public String name;
	public String spriteName;
	public int x, y;
	public Vector2i charactersPositions[];
	
	public int size()
	{
		return charactersPositions.length;
	}
	
	private RoomType(String name, String spriteName, int x, int y)
	{
		this.name = name;
		this.spriteName = spriteName;
		this.x = x;
		this.y = y;
	}

}
