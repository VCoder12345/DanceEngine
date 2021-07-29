package com.danceEngine.ai.behaviourTree;

import com.danceEngine.ecs.EComponent;

public class BehaviourTreeComponent extends EComponent {
	public BehaviourTree behaviourTree;

	public BehaviourTreeComponent(BehaviourTree behaviourTree) {
		super();
		this.behaviourTree = behaviourTree;
	}
	
	public BehaviourTreeComponent(Task...children) {
		this.behaviourTree = new BehaviourTree(children);
	}
	
}
