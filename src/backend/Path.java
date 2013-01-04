package backend;

import java.util.ArrayList;

import org.newdawn.slick.geom.Vector2f;

import backend.MathHelper;

public class Path
{
	private float data[];
	
	public Path(ArrayList<Vector2f> points)
	{
		data = new float[points.size() * 2];
		
		int i = 0;
		for(Vector2f p : points)
		{
			data[i] = p.x;
			data[i+1] = p.y;
			i += 2;			
		}
	}
	
	public Path(Path path)
	{
		data = new float[path.data.length];
		for(int i = 0; i < data.length; i++)
			data[i] = path.data[i];
	}

	public int getPointCount() {
		return data.length / 2;
	}
	
	public float getLength()
	{
		if(getPointCount() < 2)
			return 0;
		
		float d = 0;
		float x1 = getX(0);
		float y1 = getY(0);
		float x2 = 0;
		float y2 = 0;
		
		for(int i = 1; i < getPointCount(); i++)
		{
			x2 = x1;
			y2 = y1;
			
			x1 = getX(i);
			y1 = getY(i);
			
			d += MathHelper.distance(x1, y1, x2, y2);
		}
		
		return d;
	}
	
	public float getX(int i) {
		return data[2 * i];
	}
	
	public float getY(int i) {
		return data[2 * i + 1];
	}
	
	public Vector2f getPointInterpolated(float globalT)
	{
		if(getPointCount() == 0)
			return null;
		
		if(getPointCount() == 1)
			return getPoint(0);
		
		int i = (int) (Math.floor(globalT * (float)getPointCount()));
				
		if(i == getPointCount() - 1)
			return getLastPoint();
		
		float localT = MathHelper.frac(globalT * (float)getPointCount());
		Vector2f p = new Vector2f();
		p.x = MathHelper.linearInterpolation(getX(i), getX(i + 1), localT);
		p.y = MathHelper.linearInterpolation(getY(i), getY(i + 1), localT);
		return p;
	}
	
	public Vector2f getLastPoint()
	{
		return getPoint(getPointCount() - 1);
	}
	
	public Vector2f getPoint(int i)
	{
		return new Vector2f(getX(i), getY(i));
	}

	public ArrayList<Vector2f> getPointsAsList()
	{
		ArrayList<Vector2f> points = new ArrayList<Vector2f>();
		for(int i = 0; i < getPointCount(); i++)
			points.add(getPoint(i));
		return points;
	}
	
}



