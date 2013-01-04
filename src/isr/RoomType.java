package isr;

public enum RoomType {
	ENGINE ("Engine room", 3, "char1.jpg"),
	HOLD ("Hold room", 1, "char2.jpg"),
	DORM ("Dormitory", 2, "char3.jpg"),
	CELL ("Cell", 1, "char4.jpg"),
	COMMON ("Common room", 2, "char5.jpg"),
	CORRIDOR ("Corridor", 6, "char6.jpg");
	
	public String name;
	public int size;
	public String spriteName;
	
	private RoomType(String name, int size, String spriteName)
	{
		this.name = name;
		this.size = size;
		this.spriteName = spriteName;		
	}
}
