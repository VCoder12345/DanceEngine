package com.danceEngine.input.control;

public class GroupControl implements Control {
	private Control[] controls;
	
	public GroupControl(Control...controls) {
		this.controls = controls;
	}

	@Override
	public boolean isDown() {
		for(Control control: controls) {
			if(control.isDown()) {
				return true;
			}
		}
		
		return false;
	}

	@Override
	public float getValue() {
		float highestValue = 0.0f;
		for(Control control: controls) {
			float value = control.getValue();
			if(Math.abs(value) > Math.abs(highestValue)) {
				highestValue = value;
			}
		}
		
		return highestValue;
	}



}
