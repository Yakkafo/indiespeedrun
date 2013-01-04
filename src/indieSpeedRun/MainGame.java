package indieSpeedRun;


import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.AppletGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import spammer.content.Sounds;
import backend.ui.UIRenderer;
import backend.ui.UIStateBasedGame;

public class MainGame extends UIStateBasedGame
{
	public static final int INTRO_SCREEN = 1;
	public static final int GAME_PLAY = 2;
	public static final int SCORES_SCREEN = 3;
	private static boolean isApplet;

	public MainGame() {
		super("indie speed run");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {		
		try { 
		    AppGameContainer conteneur = new AppGameContainer(new MainGame());
		    conteneur.setDisplayMode(800, 600, false);
		    conteneur.setTargetFrameRate(60);
		    conteneur.setShowFPS(false);
		    conteneur.setMaximumLogicUpdateInterval(200);
		    conteneur.start();
		    //conteneur.getInput().enableKeyRepeat();
		} catch (SlickException e) { 
		    e.printStackTrace(); 
		}

	}

	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		isApplet = gc instanceof AppletGameContainer.Container;
		System.out.println("is applet : " + isApplet);
		System.out.println("Loading sounds...");
		Sounds.get().load();
		System.out.println("Loading theme...");
		SpammerTheme sTheme = new SpammerTheme();
		SpammerTheme.load();
		UIRenderer.setTheme(sTheme);
		
	}
	
	public static boolean isApplet()
	{
		return isApplet;
	}

}


