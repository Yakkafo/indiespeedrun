package backend;

public class Counter
{
	private int value;
	private int maxValue;
	
	public Counter(int maxValue)
	{
		this.maxValue = maxValue;
		value = maxValue;
	}
	
	/**
	 * Updates the counter.
	 * returns true if it finished during this update.
	 * @param delta
	 * @return
	 */
	public boolean update(int delta)
	{
		if(value != 0)
		{
			value -= delta;
			if(value <= 0)
			{
				value = 0;
				return true;
			}
		}
		else
			return true;
		return false;
	}
	
	public boolean isEnd() {
		return value == 0;
	}
	
	public void reset() {
		value = maxValue;
	}
	
	public float getProgressRatio() {
		return (float)value / (float)maxValue;
	}
	
	@Override
	public String toString() {
		return "Counter(" + value + "/" + maxValue + ")";
	}
	
	public int getMaxValue() {
		return maxValue;
	}
	
	public int getValue() {
		return value;
	}

	public void stop()
	{
		value = 0;
	}
	
}



