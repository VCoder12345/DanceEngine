package com.danceEngine.ai.behaviourTree;

import com.danceEngine.ecs.ESystem;
import com.danceEngine.ecs.Entity;

public class BehaviourTreeSystem extends ESystem {
	

	@Override
	public void afterEvents(float dt) {
		for (Entity e : getEntitiesWithTypes(BehaviourTreeComponent.class)) {
			BehaviourTree bt = e.getComponentByType(BehaviourTreeComponent.class).behaviourTree;
			TaskState state = bt.currentTask.run(e, dt);
			if(state != TaskState.RUNNING) {
				bt.currentTask = bt.currentTask.passUp(state);
			}
		}
	}

}
