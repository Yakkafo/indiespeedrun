package backend.ui;

import java.util.Collection;

public class ListLayout implements ILayout
{
	private int spacing;
	private boolean horizontal;
	
	public ListLayout()
	{
	}
	
	public ListLayout(int spacing)
	{
		this.spacing = spacing;
	}
	
	public ListLayout(int spacing, boolean horizontal)
	{
		this.spacing = spacing;
		this.horizontal = horizontal;
	}
	
	public void setHorizontal(boolean h)
	{
		horizontal = h;
	}
	
	public void doLayout(Collection<Widget> widgets, Widget container)
	{
		int x = 0;
		int y = 0;
		
		for(Widget w : widgets)
		{
			if(horizontal && w.getAlignY() != Widget.ALIGN_NONE)
				w.updateAlignY();
			else if(w.getAlignX() != Widget.ALIGN_NONE)
				w.updateAlignX();
			
			w.setPosition(x, y);
			
			if(horizontal)
				x += w.getWidth() + spacing + 2 * w.getMarginX();
			else
				y += w.getHeight() + spacing + 2 * w.getMarginY();
		}
	}
	
}



