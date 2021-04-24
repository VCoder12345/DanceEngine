package physics;

import ecs.Transform;

public class PhysicsUtils {
	public static boolean intersects(Transform t1, Transform t2) {
		return t1.position.x + t1.size.x > t2.position.x 
				&& t1.position.x < t2.position.x + t2.size.x
				&& t1.position.y + t1.size.y > t2.position.y
				&& t1.position.y < t2.position.y + t2.size.y;
	}
}
