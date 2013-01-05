package isr;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.Log;

import backend.GameComponent;
import backend.MathHelper;
import backend.geom.Rectangle;
import backend.geom.Vector2i;

public class Character
{
	private static final int INITIAL_LOYALTY[] = {40, 50, 60, 70, 80, 90};

	/**The character is loyal if his loyalty > this*/
	private static final int LOYAL_STEP = 25; 
	/**Turns before the character becomes sleepy*/
	private static final int TIME_SLEEP = 3; 
	/** Probabilty for a sabotage */
	private static final float SABOTAGE_PROBABILITY = 0.33f;
	
	private static final float SELECTION_RADIUS = 25;
	
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
	/**True if the character is selected by the player*/
	private boolean selected;
	private Image img;
	private int x, y;
	
	/**
	 * Initializes a crew for the ship.
	 * @return a Character[] where all the crew is initialized
	 */
	public static Character[] createCrew()
	{
		int nb = CharacterProfile.values().length;
		
		Character crew[] = new Character[nb];
		int temp_loyalties[] = INITIAL_LOYALTY;
		
		for(int i = 0; i < nb; i++)
		{
			int r = MathHelper.randInt(0, temp_loyalties.length-1);
			int secure = 0; //security against infinite loop
			while(temp_loyalties[r] == -1)
			{
				r++;
				secure ++;
				if(r >= nb)
					r = 0;
				if(secure >= nb)
				{
					System.out.println("Error: wrong initial loyalty.");
					break;
				}
			}
			crew[i] = new Character(i, temp_loyalties[r]);
			crew[i].enterRoom(Ship.get().getRoom(RoomType.CORRIDOR.ordinal()));
			temp_loyalties[r] = -1;
		}
		return crew;
	}
	
	/**
	 * Creates a character with the given unique ID and base loyalty.
	 * By default, it is not in any room of the ship.
	 * Note : this constructor is private because we should use createCrew instead.
	 * @param id
	 * @param loyalty
	 */
	private Character(int id, int loyalty)
	{
		if(id < CharacterProfile.values().length && id >= 0)
		{
			CharacterProfile profile = CharacterProfile.values()[id];
			
			this.selected = false;
			this.id = id;
			this.loyalty = loyalty;
			this.lastSleep = 0;
			this.nextAction = -1;
			this.name = profile.name;
			this.currentRoom = -1; // None
			this.x = 0;
			this.y = 0;

			try {
				this.img = new Image(Game.ASSETS_DIR + profile.spriteName);
			} catch (SlickException e) {
				e.printStackTrace();
			}
		}
		else
			System.out.println("Error: wrong ID!");
	}
	
	/**
	 * Makes the character change its room,
	 * assuming there is a place for him.
	 * @param room
	 */
	private void enterRoom(Room room)
	{
		if(room.isFull())
			return; // Cannot enter the room
		Vector2i pos = room.addCharacter(this);
		if(pos == null)
			return; // Cannot enter the room (but should not occur here)
		if(currentRoom >= 0)
		{
			// Quit the last room
			Ship.get().getRoom(currentRoom).removeCharacter(this);
		}
		x = pos.x + RoomType.values()[room.getType()].x;
		y = pos.y + RoomType.values()[room.getType()].y;
		currentRoom = room.getType();
		
		// Debug
		Log.debug(name + " entered in the \"" + RoomType.values()[currentRoom].name + "\"");
	}
	
	public int getNextAction()
	{
		return nextAction;
	}
	
	public void setNextAction(int n)
	{
		nextAction = n;
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
	
	public int getX()
	{
		return x;
	}
	
	public void setX(int x)
	{
		this.x = x;
	}
	
	public int getY()
	{
		return y;
	}
	
	public void setY(int y)
	{
		this.y = y;
	}
	
	public void setSelected(boolean s)
	{
		selected = s;
	}
	
	public boolean isSelected()
	{
		return selected;
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
	
	/**Check if the player clicks on the character
	 * 
	 * @param bx click x
	 * @param by click y
	 * @return
	 */
	public boolean contains(int bx, int by)
	{
		return MathHelper.distance(x, y, bx, by) < SELECTION_RADIUS;
	}

	public String toString()
	{
		String s = new String();
		s += "Name: "+name+"\n";
		s += "Loyalty: "+loyalty+"\n";
		return s;
	}

	//DISPLAY METHODS
	public void render(GameContainer gc, StateBasedGame game, Graphics gfx)
	{
		// Draw centered
		gfx.drawImage(img, x - img.getWidth() / 2, y - img.getHeight() / 2);
		
		// Debug
//		gfx.setColor(Color.cyan); // Sprite
//		gfx.drawRect(x - img.getWidth()/2, y - img.getHeight() / 2, img.getWidth(), img.getHeight());
//		gfx.setColor(Color.orange);
		if(isSelected())
		{
			float r = SELECTION_RADIUS; // Selection radius
			gfx.drawOval(x - r, y - r, 2*r, 2*r);
		}
	}
	
	public void update(GameContainer gc, StateBasedGame game, int delta)
	{
	}

}


