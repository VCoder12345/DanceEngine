package com.danceEngine.ai.behaviourTree;

public class Selector extends Composite {
	public Selector(Task...children) {
		super(children);
	}

	@Override
	public Task passUp(TaskState state) {
		if(state == TaskState.SUCCESS) {
			return handle(TaskState.SUCCESS);
		}else if(state == TaskState.FAILURE) {
			index++;
			if(index >= children.length) {
				return handle(TaskState.FAILURE);
			}
		}
		return children[index].traverse();
	}
}
