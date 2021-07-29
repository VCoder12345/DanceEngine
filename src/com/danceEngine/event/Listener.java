package com.danceEngine.event;

import java.util.function.Consumer;

public class Listener {
	protected Consumer function;
	
	public Listener(Consumer function) {
		super();
		this.function = function;
	}

	public void call(Event ev) {
		function.accept(ev);
	}
}
