package com.danceEngine.input.control;


public class JoystickButtonControl extends JoystickControl {
	private int button;
	private float pressValue;
	
	public JoystickButtonControl(int joystickId, int button, float pressValue) {
		super(joystickId);
		this.button = button;
		this.pressValue = pressValue;
	}
	
	public JoystickButtonControl(int joystickId, int button) {
		this(joystickId, button, 1);
	}

	@Override
	public boolean isDown() {
		if(joystick == null) {
			//reconnect();
			return false;
		}
		return joystick.isButtonDown(button);
	}

	@Override
	public float getValue() {
		if(isDown()) {
			return pressValue;
		}
		
		return 0;
	}

}
