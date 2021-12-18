package com.danceEngine.action;

import java.util.function.Consumer;

import com.danceEngine.ecs.Entity;

public class RunAction implements Action {
	private Consumer<Entity> consumer;
	private boolean completed = false;
	private Entity entity;
	

	public RunAction(Entity e, Consumer<Entity> consumer) {
		super();
		this.consumer = consumer;
		this.entity = e;
	}

	@Override
	public void execute(float dt) {
		consumer.accept(entity);
		completed = true;
	}

	@Override
	public boolean isComplete() {
		// TODO Auto-generated method stub
		return completed;
	}

}
