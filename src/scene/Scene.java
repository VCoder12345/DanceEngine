package scene;

import java.awt.Graphics2D;

import ecs.ESystem;
import ecs.EcsManager;
import ecs.Entity;

public abstract class Scene {
	public EcsManager ecsManager = new EcsManager();
	
	public void reset() {
		ecsManager = new EcsManager();
	}
	
	public abstract void prepare();
	
	
	public void start() {
		ecsManager.start();
	}
	
	public void update(float dt) {
		ecsManager.update(dt);
	}
	
	public void render(Graphics2D g2d) {
		ecsManager.render(g2d);
	}
	
	public void addEntity(Entity entity) {
		ecsManager.addEntity(entity);
	}
	
	public void addSystem(ESystem system) {
		ecsManager.addSystem(system);
	}

	public void runActions(float dt) {
		ecsManager.runActions(dt);
	}

}
