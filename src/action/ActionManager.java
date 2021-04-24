package action;

import java.util.ArrayList;

import ecs.EComponent;

public class ActionManager extends EComponent {
	private ArrayList<Action> actions = new ArrayList<>();
	
	public void addAction(Action action) {
		actions.add(action);
	}
	
	public void removeAllActions() {
		actions.clear();
	}
	
	public void run(float dt) {
		for(Action action : actions) {
			action.execute(dt);
		}
		
		actions.removeIf(a -> a.isComplete());
	}
}
