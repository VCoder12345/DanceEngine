package com.danceEngine.action.fsm;

import java.util.ArrayList;

import com.danceEngine.action.Action;
import com.danceEngine.action.fsm.transition.Transition;

public class State {
	public ArrayList<Action> entryActions = new ArrayList<>(), exitActions = new ArrayList<>();
	public ArrayList<Transition> transitions = new ArrayList<>();
	
	
	public ArrayList<Action> getEntryActions() {
		return entryActions;
	}

	public ArrayList<Action> getExitActions() {
		return exitActions;
	}

	public void addEntryActions(Action action) {
		entryActions.add(action);
	}
	
	public void addExitActions(Action action) {
		exitActions.add(action);
	}
	
	public void addTransition(Transition transition) {
		transitions.add(transition);
	}

	public ArrayList<Transition> getTransitions() {
		return transitions;
	}
	
	
}
