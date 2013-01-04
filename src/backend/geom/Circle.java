package backend.geom;

public class Circle implements IShape
{
	private float centerX;
	private float centerY;
	private float radius;
	
	public Circle(float x, float y, float r)
	{
		set(x, y, r);
	}
	
	public void set(Circle other)
	{
		this.centerX = other.centerX;
		this.centerY = other.centerY;
		this.radius = other.radius;
	}
	
	public void set(float x, float y, float radius)
	{
		centerX = x;
		centerY = y;
		this.radius = radius;
	}
	
	@Override
	public float getOriginX()
	{
		return centerX - radius;
	}

	@Override
	public float getOriginY()
	{
		return centerY - radius;
	}

	@Override
	public float getCenterX()
	{
		return centerX;
	}

	@Override
	public float getCenterY()
	{
		return centerY;
	}

	@Override
	public float getLength()
	{
		return radius * 2;
	}

	@Override
	public void setCenter(float x, float y)
	{
		centerX = x;
		centerY = y;
	}

	@Override
	public boolean intersects(IShape other)
	{
		if(Circle.class.isInstance(other))
		{
			// TODO Shape: Intersects Circle/Circle
			
		}
		else if(Rectangle.class.isInstance(other))
		{
			return ((Rectangle)other).intersects(this);
		}
		System.out.println("ERROR: intersects: unknown shape");
		return false;
	}

	@Override
	public void getBoundingRect(Rectangle range)
	{
		range.setEdges(
			centerX - radius, 
			centerY - radius, 
			centerX + radius, 
			centerY + radius);
	}

	@Override
	public void move(float x, float y)
	{
		centerX += x;
		centerY += y;
	}
	
}
