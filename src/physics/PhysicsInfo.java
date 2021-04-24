package physics;

import ecs.EComponent;
import physics.collision.CollisionGrid;

public class PhysicsInfo extends EComponent {
	public CollisionGrid collisionGrid;
	public float gravity;

	public PhysicsInfo(CollisionGrid collisionGrid, float gravity) {
		super();
		this.collisionGrid = collisionGrid;
		this.gravity = gravity;
	}
	
	
}
