package com.danceEngine.ai.behaviourTree;

import com.danceEngine.ecs.Entity;

public abstract class Condition extends LeafTask {
	
	public abstract boolean isTrue();

	@Override
	public TaskState run(Entity entity, float dt) {
		if(isTrue()) {
			return TaskState.SUCCESS;
		}
		
		return TaskState.FAILURE;
	}

}
