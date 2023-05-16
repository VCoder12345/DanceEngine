package com.danceEngine.ai.behavior;

public abstract class Condition extends Leaf {

	@Override
	public ReturnType update() {
		return isTrue() ? ReturnType.SUCCESS : ReturnType.FAILURE;
	}
	
	public abstract boolean isTrue();

}
