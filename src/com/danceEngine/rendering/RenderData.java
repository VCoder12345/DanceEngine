package com.danceEngine.rendering;

import com.danceEngine.camera.CameraComponent;
import com.danceEngine.ecs.Entity;
import com.danceEngine.ecs.Transform;

public class RenderData {
	public Transform cameraT;
	public CameraComponent cameraComponent;

	public RenderData(Entity camera) {
		super();
		this.cameraT = camera.getComponentByType(Transform.class);
		this.cameraComponent = camera.getComponentByType(CameraComponent.class);
	}
	
	
}
