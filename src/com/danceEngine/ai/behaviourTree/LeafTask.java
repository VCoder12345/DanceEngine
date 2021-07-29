package com.danceEngine.ai.behaviourTree;


public abstract class LeafTask extends Task {

	@Override
	public Task passUp(TaskState state) {
		return parent.passUp(state);
	}

	@Override
	public Task traverse() {
		return this;
	}

}
