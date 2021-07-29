package com.danceEngine.ai.behaviourTree;

import com.danceEngine.ecs.Entity;

public abstract class Composite extends Task {
	protected Task[] children;
	protected int index = 0;
	protected TaskState state = TaskState.RUNNING;
	
	public Composite(Task...children) {
		this.children = children;
	}

	public Task handle(TaskState state) {
		index = 0;
		return parent.passUp(state);
	}

	@Override
	public void setData(Blackboard blackboard, Task parent) {
		super.setData(blackboard, parent);
		for(Task child : children) {
			child.setData(blackboard, this);
		}
	}



	@Override
	public TaskState run(Entity entity, float dt) {
		return state;
	}

	@Override
	public Task traverse() {
		Task child = children[index];
		if(child.checkPreCondition()) {
			return child.traverse();
		}
		return children[index].traverse();
	}
}
