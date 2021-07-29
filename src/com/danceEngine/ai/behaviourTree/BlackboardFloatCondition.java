package com.danceEngine.ai.behaviourTree;

public class BlackboardFloatCondition extends BlackboardCondition {
	private float min, max;

	public BlackboardFloatCondition(String key, float min, float max) {
		super(key);
		this.min = min;
		this.max = max;
	}

	@Override
	public boolean isTrue() {
		float value = (float)blackboard.get(key);
		return  value > min && value < max;
	}

}
