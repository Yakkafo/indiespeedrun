package isr;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import backend.Counter;
import backend.ui.IActionListener;
import backend.ui.Label;
import backend.ui.RootPane;
import backend.ui.UIBasicGameState;
import backend.ui.Widget;

public class GameLoose extends UIBasicGameState
{
	private int nextState;
	private Image img;
	private Image reportImg;
	private Label report;
	private boolean printReport;
	
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException
	{
		img = new Image(Game.ASSETS_DIR + "game_over.png");
		reportImg = new Image(Game.ASSETS_DIR + "rapportfin.png");
		printReport = false;
	}
	
	@Override
	public void enter(GameContainer container, StateBasedGame game)
			throws SlickException
	{
		nextState = -1;
		super.enter(container, game);
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException
	{
		g.drawImage(img, 0, 0);
		
		if(printReport)
			g.drawImage(reportImg, 200, 0);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException
	{
		if(printReport)
			report.setVisible(true);
		if(nextState > 0)
		{
			container.exit(); // TODO REMOVE THIS SHIT
			game.enterState(nextState);
		}
	}

	@Override
	protected void createUI(GameContainer container, StateBasedGame game)
			throws SlickException
	{
		ui = new RootPane(container.getWidth(), container.getHeight()); // ecran

		report = new Label(ui, 300, 150, 400, 600, "");
		report.setVisible(false);
		report.setTextColor(Color.black);
		String text = "";
		text += "You sailed "+Ship.get().getProgressMiles()+" miles before being caught up and sunk.\n\n";
		for(int i = 0; i < 6; i++)
		{
			if(Ship.get().getCharacter(i).isLoyal())
				text += Ship.get().getCharacter(i).getName()+" was loyal to our cause and fought to the death.\n";
			else
				text += Ship.get().getCharacter(i).getName()+" were at there side in the end. He betrayed us!\n";
		}
		report.setText(text);
		ui.add(report);
	}
	
	@Override
	public void mousePressed(int button, int x, int y)
	{
		if(!printReport)
			printReport = true;
		else
			nextState = Game.MAIN_MENU;
	}
	
	@Override
	public void keyPressed(int key, char c)
	{
		nextState = Game.MAIN_MENU;
	}

	@Override
	public int getID()
	{
		return Game.GAME_LOOSE;
	}
	
}


