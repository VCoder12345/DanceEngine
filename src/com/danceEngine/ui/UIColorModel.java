package com.danceEngine.ui;

import java.awt.Color;
import java.awt.Graphics2D;

import com.danceEngine.camera.CameraComponent;
import com.danceEngine.ecs.Transform;

public abstract class UIColorModel extends UIModel {
	public Color color;
	

	public UIColorModel(Color color) {
		super();
		this.color = color;
	}


	@Override
	public void renderModel(Graphics2D g2d, Transform t, Transform camTransform, CameraComponent cameraComponent) {
		g2d.setColor(color);
		super.renderModel(g2d, t, camTransform, cameraComponent);
	}

}
