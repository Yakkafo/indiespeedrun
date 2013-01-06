package isr;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import backend.MathHelper;
import backend.ui.Label;
import backend.ui.Widget;
import backend.ui.WidgetContainer;

public class Report extends WidgetContainer{
	
	private Image background;
	private Label report;
	private String text;
	private boolean sabotage;
	
	private ArrayList<String> speakingTogetherNames;
	private ArrayList<String> speakingDuringSleepNames;
	
	//temp
	private String witness;
	private String prisonner;
	private String traitorEngine;
	private String name_with_spy;
	private ArrayList<String> roomsDiscussion;
	private boolean spyBadAction;
	private boolean tired;
	private boolean cell_fail;
	private boolean cell_win;
	private boolean enemy_ship;
	
	public Report(Widget parent, int x, int y, int width, int height) 
	{
		super(parent, x, y, width, height);
		cleanReport();
		report = new Label(this, 100, 150, 400, 600, text);
		report.setTextColor(Color.black);
		this.add(report);
		try {
			background = new Image(Game.ASSETS_DIR+"report.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		visible = false;
	}
	
	public void setSabotage(boolean sabotage)
	{
		this.sabotage = sabotage;
	}
	
	public void setCellDatas(String prisonner, boolean win, boolean fail)
	{
		this.prisonner = prisonner;
		this.cell_fail = fail;
		this.cell_win = win;
	}
	
	public void setWitness(String witness)
	{
		this.witness = witness;
	}
	
	public void setWithSpy(String name)
	{
		this.name_with_spy = name;
	}
	
	public void cleanReport()
	{
		text = "";
		tired = sabotage = false;
		name_with_spy = "";
		traitorEngine = "";
		witness = "";
		spyBadAction = cell_fail = cell_win = false;
		speakingTogetherNames = new ArrayList<String>();
		speakingDuringSleepNames = new ArrayList<String>();
		roomsDiscussion = new ArrayList<String>();
	}
	
	public void addSpeakingTogetherName(String name)
	{
		speakingTogetherNames.add(name);
	}
	
	public void addSpeakingDuringSleepName(String name)
	{
		speakingDuringSleepNames.add(name);
	}
	
	public void addDiscussionRoom(String name)
	{
		if(!roomsDiscussion.contains(name))
			roomsDiscussion.add(name);
	}
	
	/**
	 * When there is a traitor speaking with people.
	 * @param names
	 * @return
	 */
//	public String speakingTogether(ArrayList<String> names)
//	{
//		String s = "";
//		for(int i = 0; i < names.size(); i++)
//		{
//			s += names.get(i);
//			if(names.size() - i > 2)
//				s += ", ";
//			else if(names.size() - i > 1)
//				s += " & ";
//		}
//		s += " look like they had loud and suspicious discussions...";
//		return s;
//	}
	public String speakingTogether(String room)
	{
		return "Very suspicious discussions have been heard in "+room+"...\n";
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
	public String sabotageReport(ArrayList<String> names)
	{
		String s = "Captain, the engines have been sabotaged! A troublemaker is within the ship!";
		for(int i = 0; i < names.size(); i++)
		{
			s += names.get(i);
			if(names.size() - i > 1)
				s += ", ";
			else if(names.size() - i > 0)
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
	
	public void generateReport()
	{
		if(sabotage)
			text += "Captain, the engines have been sabotaged! A troublemaker is within the ship!\n";
		for(int i = 0; i < roomsDiscussion.size(); i++)
			text += speakingTogether(roomsDiscussion.get(i));
		if(!enemy_ship && EnemyShip.get().getProgressMiles() >= 0)
		{
			enemy_ship = true;
			text += "Alert! An enemy submarine is chasing us to catch back our prisonner!\n";
		}
		if(cell_win)
			text += "Congrats, Captain! "+prisonner+" was a traitor. Staying in the cells helped "+prisonner+" and the crew to forget the ennemy.\n";
		else if(cell_fail)
			text += prisonner+" seems to not be a traitor for the moment. All the crew is angry because of your decision to put "+prisonner+" in cells.\n";
		if(name_with_spy != "")
			text += "Captain, it seems that "+name_with_spy+" and our prisonner talked a lot. This one could have a bad influence on "+name_with_spy+".\n";	
		if(witness != "")
			text += witness+" heard someone sleep talking about taking over the ship.\n";
		if(spyBadAction)
			text += "Captain, our prisonner was able to send a radio communication to His allies! Expect opposition to reach us sooner! We need to watch Him more carefully.\n";
		else if(MathHelper.randFloat(0, 1) <= 0.2f)
			text += "Our prisonner was very calm today. But be aware he could send information about our position to his submarine if we don't watch him!\n";
		if(traitorEngine != "")
			text += traitorEngine+" didn’t look very efficient during his shift.\n";
		if(tired)
			text += "Some sailors look exhausted: look out for nervous breakdown.\n";
		
		
		if(text == "")
			text = "Nothing to report for today.\n";
		report.setText(text);
	}
	
	@Override
	public void render(GameContainer gc, Graphics gfx) {
		if(visible)
		{
			background.draw(getX(), getY());
			super.render(gc, gfx);
		}
		
	}

	public void setSpyBadAction(boolean b) {
		spyBadAction = b;		
	}

	public void setTraitorEngine(String name) {
		traitorEngine = name;
		
	}

	public void setTired(boolean b) {
		tired = b;
		
	}
	
	
}
