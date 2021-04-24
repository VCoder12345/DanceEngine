package rendering;

import java.awt.Color;
import java.awt.Graphics2D;

import camera.CameraComponent;
import ecs.Transform;

public abstract class ColorModel extends Model {
	public Color color;

	public ColorModel(Color color) {
		super();
		this.color = color;
	}

	@Override
	public void renderModel(Graphics2D g2d, Transform t, Transform camTransform, CameraComponent cameraComponent) {
		g2d.setColor(color);
		super.renderModel(g2d, t, camTransform, cameraComponent);
	}
	
	
}
