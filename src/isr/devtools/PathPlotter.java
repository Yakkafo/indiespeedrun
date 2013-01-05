package isr.devtools;

import java.util.ArrayList;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import backend.geom.Vector2i;

public class PathPlotter extends BasicGame
{
	private static final float POINT_RADIUS = 10;
	private static final Color fillColor = new Color(255,255,255,128);
	private static final Color borderColor = new Color(255,255,255,255);
	
	private Image backgroundImage;
	private ArrayList<Vector2i> points = new ArrayList<Vector2i>();
	
	public static void main(String args[])
	{
		AppGameContainer app;
		try
		{
			app = new AppGameContainer(new PathPlotter());
			app.setDisplayMode(800, 600, false);
			app.start();
		}
		catch (SlickException e)
		{
			e.printStackTrace();
		}
	}
	
	public PathPlotter()
	{
		super("Mon super pointeur");
	}

	@Override
	public void init(GameContainer container) throws SlickException
	{
		backgroundImage = new Image("pointage.png");
	}

	@Override
	public void render(GameContainer container, Graphics gfx)
			throws SlickException
	{
		gfx.setBackground(Color.darkGray);
		gfx.drawImage(backgroundImage, 0, 0);

		float r = POINT_RADIUS;
		for(Vector2i p : points)
		{
			gfx.setColor(fillColor);
			gfx.fillOval(p.x - r, p.y - r, 2.f*r, 2.f*r);
			gfx.setColor(borderColor);
			gfx.drawOval(p.x - r, p.y - r, 2.f*r, 2.f*r);
		}
	}

	@Override
	public void update(GameContainer container, int delta)
			throws SlickException
	{
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mousePressed(int button, int x, int y)
	{
		points.add(new Vector2i(x, y));
	}

}


