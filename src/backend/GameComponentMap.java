package backend;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.Log;

import backend.geom.Rectangle;
import backend.render.SortedRender;

/**
 * GameComponent container, providing easier methods for handling a group of components.
 * This container must be used in a context where it can be updated at each frame of the game.
 * Note : it behaves similarly to a map, but all add/remove methods are replaced by stage and dispose methods.
 * @author Marc
 *
 */
public class GameComponentMap implements Externalizable
{
	private static final long serialVersionUID = 1L;
	
	/** The main entity container **/
	private HashMap<Integer, GameComponent> components;
	
	/** Entities added while iterating on the main container **/
	private transient HashMap<Integer, GameComponent> stagedComponents;
	
	/** Entities removed while iterating on the main container **/
	private transient ArrayList<Integer> disposedComponentsIDs;
	
	/**
	 * Constructs an empty GameComponent map
	 */
	public GameComponentMap()
	{
		components = new HashMap<Integer, GameComponent>();
		stagedComponents = new HashMap<Integer, GameComponent>();
		disposedComponentsIDs = new ArrayList<Integer>();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void readExternal(ObjectInput oi) throws IOException, ClassNotFoundException
	{
		components = (HashMap<Integer, GameComponent>)oi.readObject(); // we know it's a map (see writeExternal)
		stagedComponents = new HashMap<Integer, GameComponent>();
		disposedComponentsIDs = new ArrayList<Integer>();
	}

	@Override
	public void writeExternal(ObjectOutput oo) throws IOException
	{
		flush();
		oo.writeObject(components);
	}
	
	public int size()
	{
		return components.size();
	}
	
	/**
	 * Stages a new component that will be added on next update
	 * @param cmp
	 * @return
	 */
	public void stageComponent(GameComponent e)
	{
		if(e == null)
		{
			Log.error("ERROR: null entity added to an entity map !");
			return;
		}
		stagedComponents.put(e.getID(), e);
	}
	
	/**
	 * Prepares a component to be disposed on next update
	 * @param cmpID : ID of the entity to remove
	 */
	public void disposeComponent(int ID)
	{
		GameComponent e = get(ID);
		if(e != null)
			e.dispose();
	}
	
	/**
	 * Gets a component from its ID
	 * @param ID : ID of the entity
	 * @return the component, null if not in the container
	 */
	public GameComponent get(int ID)
	{
		return components.get(ID);
	}
	
	/**
	 * Gets the contained entities as a collection.
	 * Note : do not modify this collection, better use it read-only
	 * @return game components contained in the map as a Collection
	 */
	public Collection<GameComponent> asCollection()
	{
		return components.values();
	}
	
	public void clear()
	{
		for(GameComponent e : components.values())
			e.dispose();
		flush();
	}
	
	/**
	 * Updates all game components contained in the map.
	 * Staged components are added and updated, and disposed component are erased.
	 * @param gc
	 * @param game
	 * @param delta
	 */
	public void updateAll(GameContainer gc, StateBasedGame game, int delta)
	{
		for(GameComponent e : components.values())
		{
			if(e.isDisposed())
				disposedComponentsIDs.add(e.getID());
			else
				e.update(gc, game, delta);
		}
		
		flush();
	}
	
	/**
	 * Puts staged components in the main map and call their onInit() method,
	 * calls onDestruction() to each disposed components and clears them.
	 * It is called before serialization to make sure that all relevant components will be saved.
	 */
	public void flush()
	{
		for(GameComponent e : stagedComponents.values())
		{
			components.put(e.getID(), e);
			if(!e.isInitialized())
				e.onInit();
		}
		stagedComponents.clear();
		
		for(Integer id : disposedComponentsIDs)
		{
			GameComponent e = components.get(id);
			e.onDestruction();
			components.remove(id);
		}
		disposedComponentsIDs.clear();
	}

	/**
	 * Renders all components without check if they are in the field of view or something.
	 * (only the isVisible() flag is checked)
	 * @param gc
	 * @param game
	 * @param gfx
	 */
	public void renderAll(GameContainer gc, StateBasedGame game, Graphics gfx)
	{
		for(GameComponent e : components.values())
		{
			if(e.isVisible())
				e.render(gc, game, gfx);
		}
	}
	
	/**
	 * Renders all components that are contained into the given field of view.
	 * Enables sorted rendering using IRenderable.getDrawOrder() if sort is true.
	 * @param gc
	 * @param game
	 * @param gfx
	 * @param viewBounds
	 * @param sort
	 */
	public void renderAll(
			GameContainer gc, StateBasedGame game, 
			Graphics gfx, Rectangle viewBounds, boolean sort)
	{
		Rectangle cmpBounds = new Rectangle();
		SortedRender sr = sort ? new SortedRender() : null;
		
		for(GameComponent c : components.values())
		{
			if(c.isVisible())
			{
				c.getRenderBounds(cmpBounds);
				if(cmpBounds.intersects(viewBounds))
				{
					if(sort)
						sr.add(c);
					else
						c.render(gc, game, gfx);
				}
			}
		}
		
		if(sr != null)
			sr.render(gc, game, gfx);
	}
	
}




