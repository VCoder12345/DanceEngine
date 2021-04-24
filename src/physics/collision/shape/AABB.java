package physics.collision.shape;

import maths.Vector2;

public class AABB extends CollisionShape {
	public Vector2 offset, size;

	public AABB(Vector2 offset, Vector2 size) {
		super();
		this.offset = offset;
		this.size = size;
	}
	
	public AABB(float offsetx, float offsety, float sizex, float sizey) {
		this(new Vector2(offsetx, offsety), new Vector2(sizex, sizey));
	}
	
}
