package backend.render;

import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;


/**
 * Simple ordered rendering using a map of lists containing renderable stuff.
 * Ordering is done using an integer value.
 * @author Marc
 *
 */
public class SortedRender
{
	private TreeMap<Integer, LinkedList<IRenderable> > renders;
	
	public SortedRender()
	{
		renders = new TreeMap<Integer, LinkedList<IRenderable> >();
	}
	
	public void add(IRenderable render)
	{
		int d = render.getDrawOrder();
		LinkedList<IRenderable> list = renders.get(d);
		if(list == null)
		{
			list = new LinkedList<IRenderable>();
			renders.put(d, list);
		}
		list.add(render);
	}
	
	public void render(GameContainer gc, StateBasedGame game, Graphics gfx)
	{
		for(List<IRenderable> list : renders.values())
		{
			for(IRenderable r : list)
				r.render(gc, game, gfx);
		}
	}
	
	public void clear()
	{
		renders.clear();
	}
	
}


