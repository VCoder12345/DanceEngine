package com.danceEngine.action;

public class SequenceAction implements Action {
	private Action[] actions;
	private int index = 0;
	private boolean complete = false;
	
	public SequenceAction(Action...actions) {
		this.actions = actions;
	}

	@Override
	public void execute(float dt) {
		if(complete) {
			return;
		}
		
		actions[index].execute(dt);
		if(actions[index].isComplete()) {
			index++;
			
			if(index >= actions.length) {
				complete = true;
			}
		}
	}
	

	@Override
	public boolean isComplete() {
		// TODO Auto-generated method stub
		return complete;
	}

}
