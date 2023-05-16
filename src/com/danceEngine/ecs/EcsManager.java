package com.danceEngine.ecs;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashMap;

public class EcsManager {
	public ArrayList<Entity> entities = new ArrayList<>();
	public HashMap<String, Entity> entityNameMap = new HashMap<>();
	public ArrayList<ESystem> systems = new ArrayList<>();
	
	
	public ArrayList<Entity> getEntitiesWithTypes(Class...types) {
		return getEntitiesWithTypes(getEntities(), types);
	}
	
	public ArrayList<Entity> getAllEntitiesWithTypes(Class...types) {
		return getEntitiesWithTypes(getAllEntities(), types);
	}
	
	public ArrayList<Entity> getEntitiesWithTypes(ArrayList<Entity> entities, Class...types) {
		ArrayList<Entity> tEntities = new ArrayList<>();
		for(Entity entity : entities) {
			if(entity.hasComponentTypes(types)) {
				tEntities.add(entity);
			}
		}
		
		return tEntities;
	}
	
	public ArrayList<Entity> getEntities() {
		return getActiveEntities(entities);
	}
	
	public ArrayList<Entity> getAllEntities() {
		return getAllEntities(entities);
	}
	
	public ArrayList<Entity> getActiveEntities(ArrayList<Entity> ents) {
		ArrayList<Entity> activeEntities = new ArrayList<>();
		for(Entity entity : ents) {
			if(entity.enabled) {
				activeEntities.add(entity);
				activeEntities.addAll(getActiveEntities(entity.getChildren()));
			}
		}
		
		return activeEntities;
	}
	
	public ArrayList<Entity> getAllEntities(ArrayList<Entity> ents) {
		ArrayList<Entity> activeEntities = new ArrayList<>();
		for(Entity entity : ents) {
			activeEntities.add(entity);
			activeEntities.addAll(getAllEntities(entity.getChildren()));
		}
		
		return activeEntities;
	}
	
	public Entity getEntityWithName(String name) {
		return entityNameMap.get(name);
	}
	
	public ArrayList<Entity> getEntitiesWithTag(int tag) {
		ArrayList<Entity> tEntities = new ArrayList<>();
		for(Entity entity : getEntities()) {
			if(entity.hasTag(tag)) {
				tEntities.add(entity);
			}
		}
		return tEntities;
	}
	
	
	public void update(float dt) {
		for(ESystem sys : systems) {
			sys.preUpdate(dt);
		}
		
		for(ESystem sys : systems) {
			sys.update(dt);
		}
		
		for(ESystem sys : systems) {
			sys.lateUpdate(dt);
		}
		
		doPhysics(dt);

	}
	
	private void doPhysics(float dt) {
		for(ESystem sys : systems) {
			sys.prePhysics(dt);
		}
		
		for(ESystem sys : systems) {
			sys.didPrePhysics(dt);
		}
		
		for(ESystem sys : systems) {
			sys.physics(dt);
		}
		
		
		for(ESystem sys : systems) {
			sys.didPhysics(dt);
		}
		
		//update children position
		
	}
	
	public void runActions(float dt) {
		for(Entity entity : getEntities()) {
			entity.actionManager.run(dt);
		}
		
		for(ESystem sys : systems) {
			sys.afterActions(dt);
		}
	}
	
	public void afterEvents(float dt) {
		for(ESystem sys : systems) {
			sys.afterEvents(dt);
		}
	}
	
	public void render(Graphics2D g2d) {
		for(ESystem sys : systems) {
			sys.willRender();
		}
		
		for(ESystem sys : systems) {
			sys.render(g2d);
		}
	}
	
	public void addEntity(Entity entity) {
		entities.add(entity);
		addEntityName(entity);
	}
	
	private void addEntityName(Entity entity) {
		entityNameMap.put(entity.name, entity);
	}
	
	public void addSystem(ESystem system) {
		systems.add(system);
	}

	public void start() {
		for(ESystem sys : systems) {
			sys.start();
		}
	}
	
	public void removeEntity(Entity entity) {
		if(entity.parent == null) {
			entities.remove(entity);
			entityNameMap.remove(entity.name);
		}else {
			entity.parent.removeChild(entity);
		}
		
	}
}
