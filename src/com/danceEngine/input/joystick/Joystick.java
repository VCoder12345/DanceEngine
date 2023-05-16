package com.danceEngine.input.joystick;


import java.util.HashMap;

import com.danceEngine.event.EventSystem;

import net.java.games.input.Component;
import net.java.games.input.Component.Identifier;
import net.java.games.input.Component.POV;
import net.java.games.input.Controller;
import net.java.games.input.EventQueue;

public class Joystick {
	private Controller controller;
	private boolean[] buttons = new boolean[20];
	private HashMap<String, Float> axes = new HashMap<>();
	public static final int A = 0;
	public static final int B = 1;
	public static final int X = 2;
	public static final int Y = 3;
	public static final int LB = 4;
	public static final int RB = 5;
	public static final int BACK = 6;
	public static final int START = 7;
	public static final int LS = 8;
	public static final int RS = 9;
	public static final int LEFT = 10;
	public static final int RIGHT = 11;
	public static final int UP = 12;
	public static final int DOWN = 13;
	//RT & RS sind analog --> axis: "z"
	
	
	public Joystick(Controller controller) {
		super();
		this.controller = controller;
	}

	public void update() {
		controller.poll();
		EventQueue queue = controller.getEventQueue();

		/* Create an event object for the underlying plugin to populate */
		net.java.games.input.Event event = new net.java.games.input.Event();

		/* For each object in the queue */
		while (queue.getNextEvent(event)) {
			float value = event.getValue();
			Component comp = event.getComponent();
			Identifier id = comp.getIdentifier();
			String name = id.getName();
			if(comp.isAnalog()) {
				axes.put(name, value);
			}else {
				if(name == "pov") {
					if(value == POV.LEFT) {
						buttons[LEFT] = true;
					}else if(value == POV.RIGHT) {
						buttons[RIGHT] = true;
					}else if(value == POV.UP) {
						buttons[UP] = true;
					}else if(value == POV.DOWN) {
						buttons[DOWN] = true;
					}else {
						buttons[LEFT] = false;
						buttons[RIGHT] = false;
						buttons[UP] = false;
						buttons[DOWN] = false;
					}
				}else {
					boolean pressed = (value == 1);
					int btn = Integer.parseInt(name);
					buttons[btn] = pressed;
					EventSystem.submit(new JoystickBtnEvent(btn, pressed));
				}
				
			}
		}
	}
	
	public boolean isButtonDown(int btn) {
		return buttons[btn];
	}
	
	public float getAxis(String name) {
		Float val = axes.get(name);
		if(val == null) {
			return 0;
		}
		
		return val;
	}
	




}
