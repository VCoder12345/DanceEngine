package com.danceEngine.input;

import com.danceEngine.event.Event;

public class KeyPressed extends Event {
	public int key;

	public KeyPressed(int key) {
		super();
		this.key = key;
	}
	
	
}
