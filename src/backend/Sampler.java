package backend;

/**
 * Sampled numerical function y(x).
 * Useful for signal processing, fast mathematics or custom graphs.
 * @author Marc
 *
 */
public class Sampler
{
	private float y[];
	private float minX;
	private float maxX;
	private boolean periodic;
	
	public Sampler(float yData[], float minX, float minY, boolean periodic)
	{
		y = yData;
		this.minX = minX;
		this.maxX = maxX;
		this.periodic = periodic;
	}
	
	public int getSampleCount()
	{
		return y.length;
	}
	
	public float getSample(int i)
	{
		return y[i];
	}
	
	public float get(float x)
	{
		int i = (int)((float)y.length * (x - minX) / (maxX - minX));
		float f = MathHelper.frac(x);
		
		if(periodic)
			i = i % y.length;
		else
		{
			if(i < 0)
				i = 0;
			if(i >= y.length)
				i = y.length - 1;
		}
		
		if(i + 1 < y.length)
			return MathHelper.linearInterpolation(y[i], y[i+1], f);
		else
			return y[i];
	}
	
}




