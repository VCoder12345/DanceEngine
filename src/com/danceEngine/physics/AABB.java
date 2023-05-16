package com.danceEngine.physics;

import com.danceEngine.ecs.Transform;
import com.danceEngine.utils.Vector2;

public class AABB {
	public Vector2 pos;
	public Vector2 size;
	
	public AABB(float x, float y, float w, float h) {
		this(new Vector2(x, y), new Vector2(w, h));
	}

	public AABB(Vector2 pos, Vector2 size) {
		super();
		this.pos = pos;
		this.size = size;
	}

	public AABB scale(float x) {
		return new AABB(pos.mul(x), size.mul(x));
	}
	
	
	public Vector2 getMin(Transform t) {
		return t.position.add(pos);
	}
	
	public Vector2 getMin(Vector2 p) {
		return p.add(pos);
	}
	
	public Vector2 getMax(Transform t) {
		return getMin(t).add(size);
	}
	
}
