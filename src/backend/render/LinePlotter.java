package backend.render;

import org.newdawn.slick.Graphics;

/**
 * Linked-line rendering support for Slick2D
 * @author Marc
 *
 */
public class LinePlotter
{
	private boolean firstPoint;
	private float curX;
	private float curY;
	private float lastX;
	private float lastY;
	
	public LinePlotter()
	{
		start();
	}
	
	public void start()
	{
		firstPoint = true;
	}
	
	public void plot(Graphics gfx, float x, float y)
	{
		lastX = curX;
		lastY = curY;
		
		curX = x;
		curY = y;
		
		if(firstPoint)
			firstPoint = false;
		else
		{
			gfx.drawLine(lastX, lastY, curX, curY);
//			drawLineExperimental(gfx, lastX, lastY, curX, curY);
		}
	}
	
//	public static void drawLineExperimental(Graphics gfx, float x1, float y1, float x2, float y2)
//	{
//		Vector2f v = new Vector2f(x2 - x1, y2 - y1);
//		final float t = (float)v.getTheta();
//		final float d = v.length();
//		final float r = 0.5f * gfx.getLineWidth();
//		
//		gfx.pushTransform();
//		gfx.translate(x1, y1);
//		
//		gfx.fillOval(-r, -r, 2f*r, 2f*r, 6);
//		gfx.fillOval(x2-r, y2-r, 2f*r, 2f*r, 6);
//		
//		gfx.rotate(0, 0, t);
//		
//		gfx.fillRect(0, -r, d, 2f*r);
//		
//		gfx.popTransform();
//	}
	
}

