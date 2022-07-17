package com.danceEngine.input;

import com.danceEngine.event.Event;

public class MouseWheelMoved extends Event {
	public int wheelRotation;

	public MouseWheelMoved(int wheelRotation) {
		super();
		this.wheelRotation = wheelRotation;
	}
	
	
}
