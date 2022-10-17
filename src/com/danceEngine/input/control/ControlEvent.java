package com.danceEngine.input.control;

import com.danceEngine.event.Event;

public class ControlEvent extends Event {
	public String name;

	public ControlEvent(String name) {
		super();
		this.name = name;
	}
	
	
}
