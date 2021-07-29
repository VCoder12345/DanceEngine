package com.danceEngine.input.control;

import com.danceEngine.input.Input;
import com.danceEngine.input.joystick.Joystick;

public abstract class JoystickControl implements Control {
	protected int joystickId;
	protected Joystick joystick;

	public JoystickControl(int joystickId) {
		super();
		this.joystickId = joystickId;
		this.joystick = Input.getJoystick(joystickId);
	}
	
	

}
