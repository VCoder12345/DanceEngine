package com.danceEngine.ai.behaviourTree;

public class BlackboardBoolCondition extends BlackboardCondition {

	public BlackboardBoolCondition(String key) {
		super(key);
	}

	@Override
	public boolean isTrue() {
		return (boolean)blackboard.get(key);
	}

}
