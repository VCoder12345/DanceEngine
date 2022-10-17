package com.danceEngine.input.control;

import com.danceEngine.input.Input;
import com.danceEngine.input.joystick.Joystick;

public abstract class JoystickControl extends Control {
	protected int joystickId;
	protected Joystick joystick = null;

	public JoystickControl(int joystickId) {
		super();
		this.joystickId = joystickId;
		if(joystickId < Input.getNumJoysticks()) {
			this.joystick = Input.getJoystick(joystickId);
		}
	}
	

	public void reconnect() {
		if(joystickId < Input.getNumJoysticks()) {
			this.joystick = Input.getJoystick(joystickId);
		}
	}

}
