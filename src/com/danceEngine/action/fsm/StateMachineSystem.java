package com.danceEngine.action.fsm;

import java.util.ArrayList;

import com.danceEngine.action.Action;
import com.danceEngine.action.fsm.transition.Transition;
import com.danceEngine.ecs.ESystem;
import com.danceEngine.ecs.Entity;

public class StateMachineSystem extends ESystem {


	@Override
	public void lateUpdate(float dt) {
		for (Entity e : getEntitiesWithTypes(StateMachine.class)) {
			StateMachine sm = e.getComponentByType(StateMachine.class);
			for(Transition transition : sm.currentState.getTransitions()) {
				if(transition.isTriggered()) {
					ArrayList<Action> actions = new ArrayList<Action>();
					actions.addAll(sm.currentState.getExitActions());
					sm.currentState = transition.getState();
					actions.addAll(sm.currentState.getEntryActions());
					
					e.removeAllActions();
					for(Action action : actions) {
						e.addAction(action);
					}
					
					break;
				}
			}
			
		}
	}

}
