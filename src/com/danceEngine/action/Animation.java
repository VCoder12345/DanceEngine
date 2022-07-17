package com.danceEngine.action;

import java.awt.image.BufferedImage;

import com.danceEngine.ecs.Entity;
import com.danceEngine.rendering.Renderer;
import com.danceEngine.rendering.SpriteModel;

public class Animation implements Action {
	private SpriteModel spriteModel;
	private BufferedImage[] images;
	private int changeTime;
	private boolean loop;
	private int current = -1;
	private boolean complete = false;
	private long changeTimer;
	
	public Animation(Entity entity, int changeTime, boolean loop,  BufferedImage... images) {
		super();
		this.spriteModel = (SpriteModel) entity.getComponentByType(Renderer.class).model;
		this.images = images;
		this.loop = loop;
		this.changeTime = changeTime;
		this.changeTimer = System.currentTimeMillis();
	}
	
	public Animation(Entity entity, int changeTime, BufferedImage... images) {
		this(entity, changeTime, false, images);
	}

	@Override
	public void execute(float dt) {
		if(complete)
			return;
		
		if(System.currentTimeMillis() - changeTimer > changeTime || current < 0) {
			++current;
			if(current >= images.length) {
				if(loop) {
					current = 0;
				}else {
					complete = true;
					return;
				}
			}
			spriteModel.sprite = images[current];
			changeTimer = System.currentTimeMillis();
		}
	}

	@Override
	public boolean isComplete() {
		return complete;
	}
	
	@Override
	public void reset() {
		current = -1;
		complete = false;
	}

}
