package com.danceEngine.camera;

import com.danceEngine.ecs.EComponent;

public class CameraComponent extends EComponent {
	public float zoom;

	public CameraComponent(float zoom) {
		super();
		this.zoom = zoom;
	}

	public CameraComponent() {
		this(1.0f);
	}
	
	
}
