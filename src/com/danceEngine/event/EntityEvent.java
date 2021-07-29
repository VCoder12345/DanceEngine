package com.danceEngine.event;

import com.danceEngine.ecs.Entity;

public class EntityEvent extends Event {
	public Entity entity;

	public EntityEvent(Entity entity) {
		super();
		this.entity = entity;
	}
	
	
}
