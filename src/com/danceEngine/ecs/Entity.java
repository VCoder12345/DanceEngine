package com.danceEngine.ecs;

import java.util.ArrayList;
import java.util.HashMap;

import com.danceEngine.action.Action;
import com.danceEngine.action.ActionManager;
import com.danceEngine.game.Game;
import com.danceEngine.rendering.Model;
import com.danceEngine.rendering.Renderer;
import com.danceEngine.utils.Vector2;

public class Entity {
	public String name;
	public int tag = 0;
	private HashMap<Class, EComponent> components = new HashMap<>();
	public ActionManager actionManager = new ActionManager();
	public ArrayList<Entity> children = new ArrayList<>();
	public boolean enabled = true;
	public Entity parent = null;

	public Entity(String name) {
		super();
		this.name = name;
	}
	
	public Vector2 getAbsolutePos() {
		Transform t = getComponentByType(Transform.class);
		return parent.getComponentByType(Transform.class).position.add(t.position);
	}
	
	public Transform getAbsoluteTransform() {
		Transform t = getComponentByType(Transform.class);
		if(parent == null)
			return t;
		
		Vector2 absPos = parent.getComponentByType(Transform.class).position.add(t.position);
		return new Transform(absPos, t);
	}
	
	public void addChild(Entity entity) {
		entity.parent = this;
		this.children.add(entity);
	}
	
	public ArrayList<Entity> getChildren() {
		return children;
	}
	
	public void addAction(Action action) {
		actionManager.addAction(action);
	}
	
	public void removeAllActions() {
		actionManager.removeAllActions();
	}
	
	public void addTag(int oTag) {
		tag |= oTag;
	}
	
	public boolean hasTag(int oTag) {
		return (tag & oTag) != 0;
	}
	
	public void destroy() {
		Game.getEcsManager().removeEntity(this);
	}
	
	public Entity(String name, float x, float y, float sx, float sy, float orientation, int z) {
		this(name);
		this.addComponent(new Transform(x, y, sx, sy, orientation, z));
	}
	
	public Entity(String name, float x, float y, float sx, float sy, int z) {
		this(name);
		this.addComponent(new Transform(x, y, sx, sy, z));
	}
	
	public Entity(String name, Vector2 pos, Vector2 size, int z) {
		this(name);
		this.addComponent(new Transform(pos, size, z));
	}
	
	public void addComponent(EComponent component) {
		components.put(component.getClass(), component);
	}
	
	public void addRenderer(Model model) {
		addComponent(new Renderer(model));
	}
	
	
	public <T> T getComponentByType(Class<T> type) {
		return (T) components.get(type);
	}
	
	public boolean hasComponentType(Class type) {
		var comp = components.get(type);
		return  comp != null && comp.enabled;
	}

	public boolean hasComponentTypes(Class... types) {
		for(Class type : types) {
			if(!hasComponentType(type)) {
				return false;
			}
		}
		
		return true;
	}
	
	
}
