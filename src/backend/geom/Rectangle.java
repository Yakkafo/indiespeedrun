package backend.geom;

import org.newdawn.slick.geom.Vector2f;

public class Rectangle implements IShape
{
	private float minX;
	private float minY;
	private float maxX;
	private float maxY;
	
	public Rectangle(Rectangle b)
	{
		set(b);
	}

	public Rectangle(int x, int y, int width, int height)
	{
		setOriginAndSize(x, y, width, height);
	}

	public Rectangle()
	{
	}

	public void set(Rectangle other)
	{
		this.minX = other.minX;
		this.maxX = other.maxX;
		this.minY = other.minY;
		this.maxY = other.maxY;
	}
	
	public void setEdges(float newMinX, float newMinY, float newMaxX, float newMaxY)
	{
		this.minX = newMinX;
		this.maxX = newMaxX;
		this.minY = newMaxY;
		this.maxY = newMaxY;
	}
	
	public void setOriginAndSize(float originX, float originY, float sizeX, float sizeY)
	{
		this.minX = originX;
		this.minY = originY;
		this.maxX = originX + sizeX;
		this.maxY = originY + sizeY;
	}
	
	public void setCenterAndSize(float centerX, float centerY, float sizeX, float sizeY)
	{
		minX = centerX - sizeX / 2.f;
		minY = centerY - sizeY / 2.f;
		maxX = centerX + sizeX / 2.f;
		maxY = centerY + sizeY / 2.f;
	}

	@Override
	public void setCenter(float x, float y)
	{
		float t = getWidth() / 2.f;
		minX = x - t;
		maxX = x + t;
		t = getHeight() / 2.f;
		minY = y - t;
		maxY = y + t;
	}

	@Override
	public boolean intersects(IShape other)
	{
		if(Rectangle.class.isInstance(other))
		{
			Rectangle b = (Rectangle)other;
	        if(right() <= b.left() || b.right() <= left())
	            return false;
	        return bottom() > b.top() && top() < b.bottom();
		}
		else if(Circle.class.isInstance(other))
		{
			// TODO Shape: Intersects Rectangle/Circle
			
		}
		System.out.println("ERROR: intersects: unknown shape");
		return false;
	}

	@Override
	public float getOriginX()
	{
		return minX;
	}

	@Override
	public float getOriginY()
	{
		return minY;
	}

	@Override
	public float getCenterX()
	{
		return (minX + maxX) / 2.f;
	}

	@Override
	public float getCenterY()
	{
		return (minY + maxY) / 2.f;
	}
	
	public float getWidth()
	{
		return maxX - minX;
	}
	
	public float getHeight()
	{
		return maxY - minY;
	}

	@Override
	public float getLength()
	{
		float w = getWidth();
		float h = getHeight();
		return (float) Math.sqrt(w * w + h * h);
	}
	
	public float left()
	{
		return minX;
	}
	
	public float right()
	{
		return maxX;
	}
	
	public float top()
	{
		return minY;
	}
	
	public float bottom()
	{
		return maxY;
	}
	
	public void expand(float t)
	{
		expand(t, t);
	}
	
	public void expand(float x, float y)
	{
		minX -= x;
		maxX += x;
		minY -= y;
		maxY += y;
	}
	
	public float area()
	{
		return getWidth() * getHeight();
	}

	@Override
	public String toString()
	{
		return "{" + minX + ", " + minY + "; " + maxX + ", " + maxY + "}";
	}

	@Override
	public void getBoundingRect(Rectangle range)
	{
		range.set(this);
	}

	@Override
	public void move(float x, float y)
	{
		minX += x;
		minY += y;
		maxX += x;
		maxY += y;
	}

	public boolean contains(float x, float y)
	{
		return x > minX && x < maxX && y > minY && y < maxY;
	}

	public void expandFromPoint(Vector2f p)
	{
		if(p.x < minX)
			minX = p.x;
		if(p.y < minY)
			minY = p.y;
		if(p.x > maxX)
			maxX = p.x;
		if(p.y > maxY)
			maxY = p.y;
	}
	

}


