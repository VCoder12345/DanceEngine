package com.danceEngine.ecs;

import java.awt.Graphics2D;
import java.util.ArrayList;

import com.danceEngine.game.Game;

public class ESystem {
	
	public void start() {
	}
	
	public void afterEvents(float dt) {
	}
	
	public void preUpdate(float dt) {}

	public void update(float dt) {
	}
	
	public void lateUpdate(float dt) {
	}
	
	public void physics(float dt) {
	}
	
	
	
	public void prePhysics(float dt) {
		
	}
	
	public void didPrePhysics(float dt) {
		
	}
	
	public void didPhysics(float dt) {
	}
	
	public void willRender() {
		
	}
	
	public void render(Graphics2D g2d) {
	}
	
	public ArrayList<Entity> getEntitiesWithTypes(Class...types) {
		return Game.getEcsManager().getEntitiesWithTypes(types);
	}
	
	public ArrayList<Entity> getAllEntitiesWithTypes(Class...types) {
		return Game.getEcsManager().getAllEntitiesWithTypes(types);
	}
	
	
	public ArrayList<Entity> getEntitiesWithTag(int tag) {
		return Game.getEcsManager().getEntitiesWithTag(tag);
	}

	public void afterActions(float dt) {
	}

}
