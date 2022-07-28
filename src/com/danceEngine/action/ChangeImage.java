package com.danceEngine.action;

import java.awt.image.BufferedImage;

import com.danceEngine.ecs.Entity;
import com.danceEngine.rendering.SpriteModel;

public class ChangeImage implements Action {
	private BufferedImage image;
	private SpriteModel model;
	private boolean completed = false;
	
	public ChangeImage(Entity e, BufferedImage image) {
		model = (SpriteModel) e.getRenderModel();
		this.image = image;
	}
	@Override
	public void execute(float dt) {
		model.sprite = image;
		completed = true;
	}

	@Override
	public boolean isComplete() {
		// TODO Auto-generated method stub
		return completed;
	}

}
