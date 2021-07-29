package com.danceEngine.input.joystick;

import com.danceEngine.event.Event;

public class JoystickBtnEvent extends Event {
	private int btn;
	private boolean pressed;

	
	
	public JoystickBtnEvent(int btn, boolean pressed) {
		super();
		this.btn = btn;
		this.pressed = pressed;
	}

	public int getButton() {
		return btn;
	}
	
	public boolean isPressed() {
		return pressed;
	}
	
}
