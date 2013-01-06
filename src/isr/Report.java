package isr;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import backend.ui.Widget;
import backend.ui.WidgetContainer;

public class Report extends WidgetContainer{
	
	private Image background;
	private boolean visible;	
	
	public Report(Widget parent, int x, int y, int width, int height) 
	{
		super(parent, x, y, width, height);
		try {
			background = new Image(Game.ASSETS_DIR+"report.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		visible = false;
	}
	
	public void setVisible(boolean b)
	{
		visible = b;
	}
	
	/**
	 * When there is a traitor speaking with people.
	 * @param names
	 * @return
	 */
	public String speakingTogether(String ...names)
	{
		String s = "";
		for(int i = 0; i < names.length; i++)
		{
			s += names[0];
			if(names.length - i > 1)
				s += ", ";
			else if(names.length - i > 0)
				s += " & ";
		}
		s += " look like they had loud and suspicious discussions...";
		return s;
	}
	
	/**
	 * When a witness hears a traitor speaking in his sleep
	 * @param witness
	 * @param traitor
	 * @return
	 */
	public String speakingDuringSleepReport(String witness, String traitor)
	{
		String s = witness+" said he heard "+traitor+" sleep talking about taking over the ship !";
		return s;
	}
	
	/**
	 * When the spy is calling his ship.
	 * @return
	 */
	public String spyCallingShipReport()
	{
		return "Captain, He was able to send a radio communication to His allies! Expect opposition to reach us sooner!";
	}
	
	/**
	 * When there is a sabotage
	 * @return
	 */
	public String sabotageReport(String ...names)
	{
		String s = "Captain, the engines have been sabotaged! A troublemaker is within the ship!";
		for(int i = 0; i < names.length; i++)
		{
			s += names[0];
			if(names.length - i > 1)
				s += ", ";
			else if(names.length - i > 0)
				s += " & ";
		}
		s += " were in the engine room during the sabotage... You better keep an eye on them, captain!";
		return s;
	}
	
	/**
	 * When a sailor talks with a spy
	 * @param name
	 * @return
	 */
	public String discussionWithSpyReport(String name)
	{
		return "Captain, it seems that "+name+" and Him talked a lot…";
	}
	
	/**
	 * If a loyal sailor and a traitor are in the engine room
	 * @param name1
	 * @param name2
	 * @return
	 */
	public String delationReport(String name1, String name2)
	{
		return name1+" told us that "+name2+" didn’t look very efficient during his shift...";
	}
	
	/**
	 * When a sailor is happy in the engine room
	 * @param names
	 * @return
	 */
	public String happySailorsReport(String ...names)
	{
		String s = "";
		for(int i = 0; i < names.length; i++)
		{
			s += names[0];
			if(names.length - i > 1)
				s += ", ";
			else if(names.length - i > 0)
				s += " & ";
		}
		s += " looked really enthusiast during their cycle. Seamen like no others!";
		return s;
	}
	
	@Override
	public void render(GameContainer gc, Graphics gfx) {
		if(visible)
		{
			background.draw(getX(), getY());
			super.render(gc, gfx);
		}
		
	}
}
