package com.danceEngine.ai.behaviourTree;

import com.danceEngine.ecs.Entity;

public class Decorator extends Task {
	private Task child;

	public Decorator(Task child) {
		super();
		this.child = child;
	}

	@Override
	public TaskState run(Entity entity, float dt) {
		return null;
	}
	
	@Override
	public void setData(Blackboard blackboard, Task parent) {
		super.setData(blackboard, parent);
		child.setData(blackboard, this);
	}

	@Override
	public Task traverse() {
		return child.traverse();
	}

	@Override
	public Task passUp(TaskState state) {
		return parent.passUp(state);
	}

}
