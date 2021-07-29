package com.danceEngine.action.fsm;

import java.awt.image.BufferedImage;

import com.danceEngine.action.Action;
import com.danceEngine.ecs.Entity;
import com.danceEngine.rendering.Renderer;
import com.danceEngine.rendering.SpriteModel;

public class ChangeImage implements Action {
	private SpriteModel spModel;
	private BufferedImage newImg;
	private boolean complete = false;
	
	public ChangeImage(Entity entity, BufferedImage newImg) {
		super();
		this.spModel = (SpriteModel) entity.getComponentByType(Renderer.class).model;
		this.newImg = newImg;
	}

	@Override
	public void execute(float dt) {
		if(complete)
			return;
		spModel.sprite = newImg;
		complete = true;
	}

	@Override
	public boolean isComplete() {
		// TODO Auto-generated method stub
		return complete;
	}

}
