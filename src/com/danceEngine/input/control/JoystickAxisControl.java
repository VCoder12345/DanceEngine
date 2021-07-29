package com.danceEngine.input.control;

public class JoystickAxisControl extends JoystickControl {
	private String axisName;
	
	public JoystickAxisControl(int joystickId, String axisName) {
		super(joystickId);
		this.axisName = axisName;
	}

	@Override
	public boolean isDown() {
		return false;
	}

	@Override
	public float getValue() {
		return joystick.getAxis(axisName);
	}

}
