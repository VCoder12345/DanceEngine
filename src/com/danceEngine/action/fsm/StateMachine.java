package com.danceEngine.action.fsm;

import com.danceEngine.ecs.EComponent;

public class StateMachine extends EComponent {
	public State currentState;
	
	public StateMachine(State initialState) {
		this.currentState = initialState;
	}
}
