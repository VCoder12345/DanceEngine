package event;

import ecs.Entity;

public class EntityEvent extends Event {
	public Entity entity;

	public EntityEvent(Entity entity) {
		super();
		this.entity = entity;
	}
	
	
}
