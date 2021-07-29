package com.danceEngine.input.control;

import com.danceEngine.input.Input;

public class KeyControl implements Control {
	private int key;
	private float pressValue;
	

	

	public KeyControl(int key, float pressValue) {
		super();
		this.key = key;
		this.pressValue = pressValue;
	}
	
	
	
	public KeyControl(int key) {
		this(key, 1.0f);
	}



	@Override
	public boolean isDown() {
		return Input.isKeyDown(key);
	}

	@Override
	public float getValue() {
		if(isDown()) {
			return pressValue;
		}
		return 0;
	}

}
