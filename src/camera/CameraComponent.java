package camera;

import ecs.EComponent;

public class CameraComponent extends EComponent {
	public float zoom;

	public CameraComponent(float zoom) {
		super();
		this.zoom = zoom;
	}
	
	
}
