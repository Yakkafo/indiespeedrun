package isr;

public enum RoomType {
	ENGINE ("Engine room", 3, "engine.png", 10, 10),
	HOLD ("Hold room", 1, "hold.png", 20, 20),
	DORM ("Dormitory", 2, "dorm.png", 100, 10),
	CELL ("Cell", 1, "sousmarin.png", 10, 100),
	COMMON ("Common room", 2, "commonroom.png", 60, 60),
	CORRIDOR ("Corridor", 6, "corridor.png", 200, 200),
	COMMAND ("Command room", 0, "command.png", 550, 550);
	
	public String name;
	public int size;
	public String spriteName;
	public int x, y;
	
	private RoomType(String name, int size, String spriteName, int x, int y)
	{
		this.name = name;
		this.size = size;
		this.spriteName = spriteName;
		this.x = x;
		this.y = y;
	}
}
