package isr;


public enum CharacterProfile
{
	SARAH ("Sarah", Gender.FEMALE, "chars/1.png"),
	ELLEN ("Ellen", Gender.FEMALE, "chars/2.png"),
	LOLA ("Maria", Gender.FEMALE, "chars/3.png"),
	BRUTUS ("Brutus", Gender.MALE, "chars/4.png"),
	EDGAR ("Edgar", Gender.MALE, "chars/5.png"),
	JOHN ("John", Gender.MALE, "chars/6.png");
	
	public String name;
	public Gender sex;
	public String spriteName;
	
	private CharacterProfile(String name, Gender sex, String spriteName)
	{
		this.name = name;
		this.sex = sex;
		this.spriteName = spriteName;
	}

}


