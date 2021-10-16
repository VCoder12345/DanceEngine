package com.danceEngine.scene;

import java.awt.Graphics2D;

import com.danceEngine.ecs.ESystem;
import com.danceEngine.ecs.EcsManager;
import com.danceEngine.ecs.Entity;

public abstract class Scene {
	public EcsManager ecsManager = new EcsManager();
	private boolean persistent = false;
	private boolean startedBefore = false;
	
	public void reset() {
		ecsManager = new EcsManager();
	}
	
	public abstract void prepare();
	
	
	
	public boolean wasStartedBefore() {
		return startedBefore;
	}

	public boolean isPersistent() {
		return persistent;
	}

	public void setPersistent(boolean persistent) {
		this.persistent = persistent;
	}

	public void start() {
		startedBefore = true;
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
	
	public void afterEvents(float dt) {
		ecsManager.afterEvents(dt);
	}

	public void runActions(float dt) {
		ecsManager.runActions(dt);
	}

}
