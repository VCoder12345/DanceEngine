package com.danceEngine.action.fsm.transition;

import com.danceEngine.action.fsm.State;

public abstract class Transition {
	private State state;

	public Transition(State state) {
		super();
		this.state = state;
	}

	

	public abstract boolean isTriggered();

	public State getState() {
		return state;
	}
	
}
