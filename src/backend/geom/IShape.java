package backend.geom;

public interface IShape
{
	public float getOriginX();
	
	public float getOriginY();
	
	public float getCenterX();
	
	public float getCenterY();
	
	public float getLength();

	public void setCenter(float x, float y);
	
	public boolean intersects(IShape other);

	public void getBoundingRect(Rectangle range);
	
	public void move(float x, float y);

}


