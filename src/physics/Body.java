package physics;

import ecs.EComponent;
import utils.Vector2;

public class Body extends EComponent {
	public float mass;
	public Vector2 velocity = new Vector2();
	public Vector2 acceleration = new Vector2();
	public boolean onGround = false;
	
	
	public Body(float mass) {
		super();
		this.mass = mass;
	}
	
	public Body() {
		this(1.0f);
	}

	public void applyForce(Vector2 force) {
		Vector2 f = force.div(mass);
		acceleration.addE(f);
	}
}
