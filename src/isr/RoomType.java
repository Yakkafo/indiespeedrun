package isr;


import backend.geom.Vector2i;

public enum RoomType {
	ENGINE ("Engine room", 3, "engine.png", 10, 10),
	HOLD ("Hold room", 1, "hold.png", 800, 800),
	DORM ("Dormitory", 2, "dorm.png", 100, 10),
	CELL ("Cell", 1, "sousmarin.png", 10, 100),
	COMMON ("Common room", 2, "commonroom.png", 60, 60),
	CORRIDOR ("Corridor", 6, "corridor.png", 200, 200),
	COMMAND ("Command room", 0, "command.png", 550, 550);
	
	//Each array contains character positions for each room
	static {
		Vector2i posEngine[] = {new Vector2i(15, 15), new Vector2i(20, 20), new Vector2i(25, 25)};
		ENGINE.charactersPositions = posEngine;
		
		Vector2i posHold[] = {new Vector2i(800, 800)};
		HOLD.charactersPositions = posHold;
		
		Vector2i posDormitory[] = {new Vector2i(100, 10), new Vector2i(120, 20)};
		DORM.charactersPositions = posDormitory;
		
		Vector2i posCell[] = {new Vector2i(15, 110)};
		DORM.charactersPositions = posCell;
		
		Vector2i posCommon[] = {new Vector2i(65, 65), new Vector2i(80, 80)};
		DORM.charactersPositions = posCommon;
		
		Vector2i posCorridor[] = {new Vector2i(200, 200), new Vector2i(200, 220), new Vector2i(200, 240), new Vector2i(200, 260), new Vector2i(200, 280), new Vector2i(200, 300)};
		DORM.charactersPositions = posCorridor;
		
		Vector2i posCommand[] = {};
		DORM.charactersPositions = posCommand;
	}
	
	public String name;
	public int size;
	public String spriteName;
	public int x, y;
	public Vector2i charactersPositions[];
	
	private RoomType(String name, int size, String spriteName, int x, int y)
	{
		this.name = name;
		this.size = size;
		this.spriteName = spriteName;
		this.x = x;
		this.y = y;
	}
}
