package com.danceEngine.action;

import java.util.ArrayList;

import com.danceEngine.ecs.EComponent;

public class ActionManager extends EComponent {
	private ArrayList<Action> actions = new ArrayList<>();
	
	public void addAction(Action action) {
		actions.add(action);
	}
	
	public void removeAllActions() {
		for(Action action : actions) {
			action.end();
		}
		actions.clear();
	}
	
	public void run(float dt) {
		for(Action action : actions) {
			action.execute(dt);
		}
		
		ArrayList<Action> clone = (ArrayList<Action>) actions.clone();
		for(Action action : clone) {
			if(action.isComplete()) {
				action.end();
				actions.remove(action);
			}
		}
	}
}
