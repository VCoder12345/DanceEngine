package com.danceEngine.action;

import com.danceEngine.ecs.Entity;
import com.danceEngine.ecs.Transform;
import com.danceEngine.utils.Utils;
import com.danceEngine.utils.Vector2;

public class ScaleTo implements Action {
	private Transform transform;
	private Vector2 startSize, startPos;
	private Vector2 target;
	private int scaleTime;
	private float timer = 0;
	
	public ScaleTo(Entity e, Vector2 target, int scaleTime) {
		this.transform = e.getComponentByType(Transform.class);
		this.target = target;
		this.scaleTime = scaleTime;

	}
	
	@Override
	public void start() {
		this.startSize = transform.size.copy();
		this.startPos = transform.position.copy();
	}
	

	@Override
	public void execute(float dt) {
		timer += dt / scaleTime;
		transform.size = Utils.lerp(startSize, target, timer);
		transform.position = startPos.sub(transform.size.sub(startSize).div(2));
	}

	@Override
	public boolean isComplete() {
		return timer >= 1.0f;
	}

}
