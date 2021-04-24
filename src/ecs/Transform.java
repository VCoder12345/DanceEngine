package ecs;

import utils.Vector2;

public class Transform extends EComponent {
	public Vector2 position, size;
	public int z;
	

	public Transform(Vector2 position, Vector2 size, int z) {
		this.position = position;
		this.size = size;
		this.z = z;
	}
	
	public Transform(float x, float y, float sx, float sy, int z) {
		this(new Vector2(x, y), new Vector2(sx, sy), z);
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


	
	
}
