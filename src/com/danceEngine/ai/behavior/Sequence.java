package com.danceEngine.ai.behavior;

public class Sequence extends Composite {

	public Sequence(Node[] childs) {
		super(childs);
	}

	@Override
	public Leaf childReturn(ReturnType ret) {
		if(ret == ReturnType.RUNNING) return childs[current].findLeaf();
		
		if(ret == ReturnType.SUCCESS) {
			childs[current].terminate();
			++current;
			
			if(current >= childs.length) {
				if(parent == null) {
					terminate();
					return null;
				}
				return getParent().childReturn(ReturnType.SUCCESS); 
			}
			childs[current].init();
			return childs[current].findLeaf();
		}else {
			childs[current].terminate();

			return getParent().childReturn(ReturnType.FAILURE);
		}
	}

	

}
