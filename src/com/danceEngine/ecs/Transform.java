package com.danceEngine.ecs;

import com.danceEngine.utils.Vector2;

public class Transform extends EComponent {
	public Vector2 position, size;
	public float orientation = 0;
	public int z;
	

	public Transform(Vector2 position, Vector2 size, float orientation, int z) {
		this.orientation = orientation;
		this.position = position;
		this.size = size;
		this.z = z;
	}
	
	public Transform(Vector2 position, Vector2 size, int z) {
		this(position, size, 0, z);
	}
	
	public Transform(float x, float y, float sx, float sy, float orientation, int z) {
		this(new Vector2(x, y), new Vector2(sx, sy), orientation, z);
	}
	
	public Transform(float x, float y, float sx, float sy, int z) {
		this(new Vector2(x, y), new Vector2(sx, sy), z);
	}
	
	public Transform(float x, float y, float sx, float sy) {
		this(new Vector2(x, y), new Vector2(sx, sy), 0);
	}

	public Transform(int z) {
		this(0, 0, 0, 0, z);
	}
	
	public Transform() {
		this(0, 0, 0, 0, 0);
	}

	public Transform(float x, float y, int z) {
		this(x, y, 0, 0, z);
	}

	public Transform(Vector2 pos, Vector2 size) {
		this(pos, size, 0);
	}
	public Transform(Vector2 absPos, Transform t) {
		this(absPos, t.size, t.orientation, t.z);
	}

	public Vector2 center() {
		return position.add(size.div(2));
	}
	
	
}
