package rendering;

import camera.CameraComponent;
import ecs.Entity;
import ecs.Transform;

public class RenderData {
	public Transform cameraT;
	public CameraComponent cameraComponent;

	public RenderData(Entity camera) {
		super();
		this.cameraT = camera.getComponentByType(Transform.class);
		this.cameraComponent = camera.getComponentByType(CameraComponent.class);
	}
	
	
}
