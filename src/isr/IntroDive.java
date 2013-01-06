package isr;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import backend.Counter;
import backend.geom.Vector2i;
import backend.ui.UIBasicGameState;

public class IntroDive extends UIBasicGameState
{
	private Sound diveSound;
	private int nextState;
	private Vector2f viewOffset = new Vector2f();
	private Counter counter;
	
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException
	{
		diveSound = new Sound(Game.ASSETS_DIR + "plongee_debut.ogg");
	}
	
	@Override
	public void enter(GameContainer container, StateBasedGame game)
			throws SlickException
	{
		nextState = -1;
		super.enter(container, game);
		Ship.get().init();
		EnemyShip.get().init();
		diveSound.play();
		counter = new Counter(5000);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame game, Graphics gfx)
			throws SlickException
	{
		ScrollBackground.get().render(gfx);
		
		Vector2i shipSize = Ship.get().getBackgroundSize();
		viewOffset.x = (gc.getWidth() - shipSize.x) / 2;
		viewOffset.y = (gc.getHeight() - shipSize.y) / 2;
		// Add somme bobbing?
		float t = (float)gc.getTime() / 1000.f;
		viewOffset.x += 8.f * (float)Math.cos(t);
		viewOffset.y += 4.f * (float)Math.sin(2.f * t);
		
		gfx.pushTransform();		
		gfx.translate(viewOffset.x, viewOffset.y);
		
		t = counter.getProgressRatio();
		float x = -t * t * 1000.f;
		gfx.translate(x, 0);
		
		Ship.get().render(gc, game, gfx);
		
		gfx.popTransform();
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException
	{
		if(counter.update(delta))
			nextState = Game.GAME_PLAY;
		
		if(nextState > 0)
			game.enterState(nextState);
		
		ScrollBackground.get().update(delta);

	}

	@Override
	protected void createUI(GameContainer container, StateBasedGame game)
			throws SlickException
	{
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void keyPressed(int key, char c)
	{
		if(key == Input.KEY_SPACE)
			nextState = Game.GAME_PLAY;
	}

	@Override
	public int getID()
	{
		return Game.INTRO_DIVE;
	}

}



