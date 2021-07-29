package com.danceEngine.ai.behaviourTree;

public class BehaviourTree extends Selector {
	public Task currentTask = null;
	
	public BehaviourTree(Task...children) {
		super(children);
		this.blackboard = new Blackboard();
		for(Task child : children) {
			child.setData(blackboard, this);
		}
		this.currentTask = traverse();
	}

	@Override
	public Task handle(TaskState state) {
		index = 0;
		return traverse();
	}
	
	
	
	
}
