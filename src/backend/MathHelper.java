package backend;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.util.Log;

/**
 * Math utility methods and shortcuts
 * @author Marc
 *
 */
public class MathHelper
{
	public static final float RAD2DEG = (float) (180.f / Math.PI);
	
	/**
	 * Returns the value of f(x), where f is a slow-begin-slow-end curve equation :
	 * |       ..
	 * |     .
	 * |   .
	 * |..
	 * O---------> X
	 * @param x : value between 0 and 1
	 * @return f(x), between 0 and 1
	 */
    public static float smoothCurve(float x)
    {
        return (float) (6 * Math.pow(x,5) - 15 * Math.pow(x, 4) + 10 * Math.pow(x,3));
    }
    
    /**
     * Interpolates 2 values using the t ratio
     * @param x0 : min value
     * @param x1 : max value
     * @param t : ratio
     * @return
     */
    public static float linearInterpolation(float x0, float x1, float t)
    {
        return x0 + (x1 - x0) * t;
    }
    
    /**
     * Interpolates 4 2D values using ratios x and y
     * (Param descriptions are described as in a 2D space)
     * @param x0y0 : lower left
     * @param x1y0 : lower right
     * @param x0y1 : upper left
     * @param x1y1 : upper right
     * @param x : X-axis ratio
     * @param y : Y-axis ratio
     * @return
     */
    static float biLinearInterpolation(
			float x0y0, float x1y0,
			float x0y1, float x1y1,
			float x, float y)
    {
        float tx = smoothCurve(x);
        float ty = smoothCurve(y);

        float u = linearInterpolation(x0y0, x1y0, tx);
        float v = linearInterpolation(x0y1, x1y1, tx);

        return linearInterpolation(u, v, ty);
    }
    
	/**
	 * Subtracts a to x and set it to zero if it crosses zero.
	 * @param x : velocity
	 * @param a : reduction
	 * @return reduced parameter
	 */
	public static float diminishVelocity(float x, float a)
	{
		if(x > 0)
		{
			x -= a;
			if(x < 0)
				x = 0;
		}
		else if(x < 0)
		{
			x += a;
			if(x > 0)
				x = 0;
		}
		return x;
	}
	
	/**
	 * Computes the color mean of an image
	 * @param img
	 * @return
	 */
	public static Color mean(Image img)
	{
		Color m = new Color(0,0,0,255);
		
		int x, y;
		for(y = 0; y < img.getHeight(); y++)
		{
			for(x = 0; x < img.getWidth(); x++)
			{
				Color pix = img.getColor(x, y);
				m.r += pix.r;
				m.g += pix.g;
				m.b += pix.b;
			}
		}
		
		float d = img.getWidth() * img.getHeight();
		m.r /= d;
		m.g /= d;
		m.b /= d;
		
		return m;
	}
	
	/**
	 * Returns a random integer number between min and max (included)
	 * @param min
	 * @param max
	 * @return
	 */
	public static int randInt(int min, int max)
	{
		return (int) ((float)(max - min + 1) * Math.random() + (float)min);
	}
	
	/**
	 * Returns a random float number between min and max
	 * @param min
	 * @param max
	 * @return
	 */
	public static float randFloat(float min, float max)
	{
		return (max - min) * (float)Math.random() + (float)min;
	}
	
	/**
	 * Returns a random float number between -k and k
	 * @return
	 */
	public static float randS(float k)
	{
		return k * (float) (2.f * Math.random() - 1.f);
	}

	/**
	 * Returns the given number multiplied by itself.
	 * @param x
	 * @return x^2
	 */
	public static float sq(float x)
	{
		return x * x;
	}
	
	/**
	 * Computes an integer percentage.
	 * @param a : dividend (positive value)
	 * @param b : divider (max value of a)
	 * @return
	 */
	public static int percent(int a, int b)
	{
		return (int) (100.f * (float)a / (float)b);
	}

	public static float distance(float x, float y, float x2, float y2)
	{
		return (float) Math.sqrt(sq(x - x2) + sq(y - y2));
	}

	/**
	 * Converts the given angle from degrees to radians
	 * @param t
	 * @return
	 */
	public static float rad2deg(float t)
	{
		return t * RAD2DEG;
	}
	
	public static float deg2rad(float t)
	{
		return t / RAD2DEG;
	}
	
	/**
	 * Returns the decimal part of a floating number
	 * @param x
	 * @return
	 */
	public static float frac(float x)
	{
		return x - (float)Math.floor(x);
	}
	
	public static float fmod(float x, float d)
	{
		return d * frac(x / d);
	}
	
	public static void interpolateColor(Color c, Color color1, Color color2, float t)
	{
		c.a = t * color2.a + (1.f - t) * color1.a;
		c.r = t * color2.r + (1.f - t) * color1.r;
		c.g = t * color2.g + (1.f - t) * color1.g;
		c.b = t * color2.b + (1.f - t) * color1.b;
	}
	
	public static int pow(int x, int n)
	{
		for(int i = 0; i < n; i++)
			x *= x;
		return x;
	}
	
	public static void interpolate(float v[], float v0[], float v1[], float t)
	{
		if(v.length != v0.length || v.length != v1.length)
		{
			Log.error("Array interpolation error : sizes doesn't match");
			return;
		}
		
		for(int i = 0; i < v.length; i++)
		{
			v[i] = MathHelper.linearInterpolation(v0[i], v1[i], t);
		}
	}
	
	
}






