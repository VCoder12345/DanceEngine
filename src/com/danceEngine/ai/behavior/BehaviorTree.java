package com.danceEngine.ai.behavior;

public class BehaviorTree {
	public Node root;
	private Leaf currentLeaf = null;
	
	
	
	public BehaviorTree(Node root) {
		super();
		this.root = root;
	}

	public void update() {
		if(currentLeaf == null) {
			traverse();
		}
		
		ReturnType ret;
		do {
			ret = currentLeaf.update();
			if(currentLeaf.getParent() == null) {
				currentLeaf.terminate();
				currentLeaf = null;
				break;
			}
			Leaf lastLeaf = currentLeaf;
			currentLeaf = currentLeaf.getParent().childReturn(ret);
			
			if(currentLeaf == null) {
				traverse();
			}else if(currentLeaf != lastLeaf) {
				currentLeaf.init();
			}
			
		}while(ret != ReturnType.RUNNING);
	}
	
	public void traverse() {
		Leaf lastLeaf = currentLeaf;
		currentLeaf = root.findLeaf();
		
		if(lastLeaf != null && currentLeaf != lastLeaf) {
			lastLeaf.recursiveTerminate();
			currentLeaf.init();
		}else if(lastLeaf == null) {
			currentLeaf.init();
		}
	}
}
