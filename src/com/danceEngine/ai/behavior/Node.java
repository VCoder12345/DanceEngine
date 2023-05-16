package com.danceEngine.ai.behavior;

public abstract class Node {
	public Node parent = null;
	
	public void init() {}
	public void terminate() {}
	public abstract Leaf findLeaf();
	public abstract Leaf childReturn(ReturnType ret);
	public void recursiveTerminate() {
		terminate();
		if(parent != null) {
			parent.recursiveTerminate();
		}
	}
	
	public Node getParent() {
		return parent;
	}
}
