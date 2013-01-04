package isr;

import java.util.ArrayList;

public enum CharacterProfile
{
	SARAH ("Sarah", Gender.FEMALE, "char1.jpg"),
	ELLEN ("Ellen", Gender.FEMALE, "char2.jpg"),
	LOLA ("Sarah", Gender.FEMALE, "char3.jpg"),
	BRUTUS ("Brutus", Gender.MALE, "char4.jpg"),
	EDGAR ("Edgar", Gender.MALE, "char5.jpg"),
	JOHN ("John", Gender.MALE, "char5.jpg");
	
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


