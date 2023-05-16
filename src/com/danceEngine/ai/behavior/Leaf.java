package com.danceEngine.ai.behavior;

public abstract class Leaf extends Node {
	public abstract ReturnType update();
	
	@Override
	public Leaf findLeaf() {
		return this;
	}
	
	@Override
	public Leaf childReturn(ReturnType ret) {
		return null;
	}
}
