package isr;


public enum CharacterProfile
{
	SARAH ("Sarah", Gender.FEMALE, 0, "chars/1.png", "chars/sarah.png"),
	ELLEN ("Ellen", Gender.FEMALE, 1, "chars/5.png", "chars/ellen.png"),
	MARIA ("Maria", Gender.FEMALE, 2, "chars/3.png", "chars/maria.png"),
	BRUTUS ("Brutus", Gender.MALE, 0, "chars/4.png", "chars/brutus.png"),
	EDGAR ("Edgar", Gender.MALE, 1, "chars/2.png", "chars/edgar.png"),
	JOHN ("John", Gender.MALE, 2, "chars/6.png", "chars/john.png");
	
	public String name;
	public Gender sex;
	public String topSpriteName;
	public String faceSpriteName;
	private int sexID;
		
	public String getPositiveYesSoundName()
	{
		return getYesSoundPrefix() + "posi.ogg";
	}
	
	public String getNegativeYesSoundName()
	{
		return getYesSoundPrefix() + "nega.ogg";
	}
	
	private String getYesSoundPrefix()
	{
		String p = "";
		if(sex == Gender.MALE)
			p += "male" + (sexID + 1);
		else if(sex == Gender.FEMALE)
			p += "female" + (sexID + 1);
		p += "_";
		return p;
	}
	
	private CharacterProfile(String name, Gender sex, int sexID, String topSpriteName, String faceSpriteName)
	{
		this.name = name;
		this.sex = sex;
		this.sexID = sexID;
		this.topSpriteName = topSpriteName;
		this.faceSpriteName = faceSpriteName;
	}

}


