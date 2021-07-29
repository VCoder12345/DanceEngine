package com.danceEngine.ai.behaviourTree;

public class Sequence extends Composite {
	public Sequence(Task...children) {
		super(children);
	}

	@Override
	public Task passUp(TaskState state) {
		if(state == TaskState.FAILURE) {
			return handle(TaskState.FAILURE);
		}else if(state == TaskState.SUCCESS) {
			index++;
			if(index >= children.length) {
				return handle(TaskState.SUCCESS);
			}
		}
		
		return children[index].traverse();
	}

}
