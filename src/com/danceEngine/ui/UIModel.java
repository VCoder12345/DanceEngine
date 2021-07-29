package com.danceEngine.ui;

import java.awt.Graphics2D;

import com.danceEngine.camera.CameraComponent;
import com.danceEngine.ecs.Transform;
import com.danceEngine.rendering.Model;

public abstract class UIModel extends Model {
	

	@Override
	public void renderModel(Graphics2D g2d, Transform t, Transform camTransform, CameraComponent cameraComponent) {
		super.renderModel(g2d, t, new Transform(), cameraComponent);
	}


}
