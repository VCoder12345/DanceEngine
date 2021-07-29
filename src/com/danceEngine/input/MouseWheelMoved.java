package com.danceEngine.input;

import com.danceEngine.event.Event;

public class MouseWheelMoved extends Event {
	public int scrollAmount;

	public MouseWheelMoved(int scrollAmount) {
		super();
		this.scrollAmount = scrollAmount;
	}
	
	
}
