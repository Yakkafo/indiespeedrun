package isr;

import java.util.ArrayList;

public enum CharacterProfile
{
	SARAH ("Sarah", Gender.FEMALE, "chars/1.png", "chars/sarah.png"),
	ELLEN ("Ellen", Gender.FEMALE, "chars/5.png", "chars/ellen.png"),
	MARIA ("Maria", Gender.FEMALE, "chars/3.png", "chars/maria.png"),
	BRUTUS ("Brutus", Gender.MALE, "chars/4.png", "chars/brutus.png"),
	EDGAR ("Edgar", Gender.MALE, "chars/2.png", "chars/edgar.png"),
	JOHN ("John", Gender.MALE, "chars/6.png", "chars/john.png");
	
	public String name;
	public Gender sex;
	public String topSpriteName;
	public String faceSpriteName;
	
	private CharacterProfile(String name, Gender sex, String topSpriteName, String faceSpriteName)
	{
		this.name = name;
		this.sex = sex;
		this.topSpriteName = topSpriteName;
		this.faceSpriteName = faceSpriteName;
	}

}


