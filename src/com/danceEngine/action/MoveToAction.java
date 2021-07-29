package com.danceEngine.action;

import com.danceEngine.ecs.Entity;
import com.danceEngine.ecs.Transform;
import com.danceEngine.utils.Utils;
import com.danceEngine.utils.Vector2;

public class MoveToAction implements Action {
	private Transform transform;
	private Vector2 startPos;
	private Vector2 target;
	private float moveTime;
	private float timer = 0;
	
	public MoveToAction(Entity e, Vector2 target, long moveTime) {
		this.transform = e.getComponentByType(Transform.class);
		this.target = target;
		this.moveTime = moveTime;
		this.startPos = transform.position.copy();
	}
	

	@Override
	public void execute(float dt) {
		timer += dt / moveTime;
		transform.position = Utils.lerp(startPos, target, timer);
	}

	@Override
	public boolean isComplete() {
		// TODO Auto-generated method stub
		return timer >= 1.0f;
	}

}
