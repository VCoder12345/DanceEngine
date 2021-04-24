package ecs;

import java.awt.Graphics2D;
import java.util.ArrayList;

import game.Game;

public class ESystem {
	
	public void start() {
	}

	public void update(float dt) {
	}
	
	public void lateUpdate(float dt) {
	}
	
	public void physics(float dt) {
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
	
	public ArrayList<Entity> getEntitiesWithTag(int tag) {
		return Game.getEcsManager().getEntitiesWithTag(tag);
	}

}
