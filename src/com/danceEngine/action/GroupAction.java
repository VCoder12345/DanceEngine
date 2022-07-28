package com.danceEngine.action;

import java.util.ArrayList;

public class GroupAction implements Action {
	private Action[] actions;
	private ArrayList<Action> actionsRunning;
	
	public GroupAction(Action...actions) {
		this.actions = actions;
		

	}
	
	@Override
	public void start() {
		actionsRunning = new ArrayList<>();
		for(Action action : actions) {
			actionsRunning.add(action);
			action.start();
		}
	}

	@Override
	public void execute(float dt) {
		ArrayList<Action> clone = (ArrayList<Action>) actionsRunning.clone();
		for(Action action : clone) {
			action.execute(dt);
			if(action.isComplete()) {
				actionsRunning.remove(action);
			}
		}
	}
	

	@Override
	public boolean isComplete() {
		return actionsRunning.size() == 0;
	}

}
