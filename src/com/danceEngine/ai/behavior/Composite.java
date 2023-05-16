package com.danceEngine.ai.behavior;

public abstract class Composite extends Node {
	protected Node[] childs;
	protected int current = 0;
	
	

	public Composite(Node[] childs) {
		super();
		this.childs = childs;
		
		for(Node child : childs) {
			child.parent = this;
		}
	}
	
	@Override
	public Leaf findLeaf() {
		return childs[current].findLeaf();
	}
	
	@Override
	public void terminate() {
		current = 0;
	}

}
