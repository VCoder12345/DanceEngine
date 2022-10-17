package com.danceEngine.input.control;

public abstract class Control {
	public boolean wasDown = false;
	public boolean sendsEvents = false;
	
	public abstract boolean isDown();
	public abstract float getValue();
}
