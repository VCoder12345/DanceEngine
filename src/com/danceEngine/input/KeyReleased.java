package com.danceEngine.input;

import com.danceEngine.event.Event;

public class KeyReleased extends Event {
	public int key;

	public KeyReleased(int key) {
		super();
		this.key = key;
	}
	
	
}
