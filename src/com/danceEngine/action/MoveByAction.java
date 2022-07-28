package com.danceEngine.action;

import com.danceEngine.ecs.Entity;
import com.danceEngine.ecs.Transform;
import com.danceEngine.utils.Utils;
import com.danceEngine.utils.Vector2;

public class MoveByAction extends MoveToAction {

	public MoveByAction(Entity e, Vector2 distance, int moveTime) {
		super(e, e.getComponentByType(Transform.class).position.add(distance), moveTime);
		// TODO Auto-generated constructor stub
	}
	

}
