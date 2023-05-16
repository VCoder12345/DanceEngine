package com.danceEngine.ai.behavior;

public class Selector extends Composite {

	public Selector(Node[] childs) {
		super(childs);
	}

	@Override
	public Leaf childReturn(ReturnType ret) {
		if(ret == ReturnType.RUNNING) return childs[current].findLeaf();
		
		if(ret == ReturnType.FAILURE) {
			childs[current].terminate();
			++current;
			
			if(current >= childs.length) {
				if(parent == null) {
					terminate();
					return null;
				}
				return getParent().childReturn(ReturnType.FAILURE); 
			}
			childs[current].init();
			return childs[current].findLeaf();
		}else {
			childs[current].terminate();
			
			return getParent().childReturn(ReturnType.SUCCESS);
		}
	}

	

}
