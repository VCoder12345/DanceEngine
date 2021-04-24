package ecs;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashMap;

public class EcsManager {
	public ArrayList<Entity> entities = new ArrayList<>();
	public HashMap<String, Entity> entityNameMap = new HashMap<>();
	public ArrayList<ESystem> systems = new ArrayList<>();
	
	
	public ArrayList<Entity> getEntitiesWithTypes(Class...types) {
		ArrayList<Entity> tEntities = new ArrayList<>();
		for(Entity entity : entities) {
			if(entity.hasComponentTypes(types)) {
				tEntities.add(entity);
			}
		}
		
		return tEntities;
	}
	
	public Entity getEntityWithName(String name) {
		return entityNameMap.get(name);
	}
	
	public ArrayList<Entity> getEntitiesWithTag(int tag) {
		ArrayList<Entity> tEntities = new ArrayList<>();
		for(Entity entity : entities) {
			if(entity.hasTag(tag)) {
				tEntities.add(entity);
			}
		}
		return tEntities;
	}
	
	public void update(float dt) {
		for(ESystem sys : systems) {
			sys.update(dt);
		}
		
		
		
		for(ESystem sys : systems) {
			sys.lateUpdate(dt);
		}

		
		for(ESystem sys : systems) {
			sys.physics(dt);
		}
		
		for(ESystem sys : systems) {
			sys.didPhysics(dt);
		}

	}
	
	public void runActions(float dt) {
		for(Entity entity : entities) {
			entity.actionManager.run(dt);
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
		entities.remove(entity);
		entityNameMap.remove(entity.name);
	}
}
