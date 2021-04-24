package physics.collision;

import ecs.EComponent;
import physics.collision.shape.CollisionShape;

public class Collider extends EComponent {
	public CollisionShape shape;
	public boolean trigger;
	
	public Collider(CollisionShape shape, boolean trigger) {
		super();
		this.shape = shape;
		this.trigger = trigger;
	}
	
	public Collider(CollisionShape shape) {
		this(shape, false);
	}
}
