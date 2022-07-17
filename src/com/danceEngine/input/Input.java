package com.danceEngine.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import com.danceEngine.ecs.DataLocator;
import com.danceEngine.event.EventSystem;
import com.danceEngine.game.Game;
import com.danceEngine.input.control.Control;
import com.danceEngine.input.joystick.Joystick;
import com.danceEngine.rendering.RenderData;
import com.danceEngine.utils.Vector2;

import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;

public class Input implements KeyListener, MouseListener, MouseMotionListener, MouseWheelListener {
	private static boolean[] keys = new boolean[1024];
	private static boolean mousePressed = false;
	private static Vector2 mousePosition = new Vector2();
	private static ArrayList<Joystick> joysticks = new ArrayList<>();
	private static HashMap<String, Control> controls = new HashMap<>();
	public static boolean enableJInput = true;
	private static int mouseBtn = MouseEvent.NOBUTTON;
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	public static int getNumJoysticks() {
		return joysticks.size();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
		EventSystem.submit(new KeyPressed(e.getKeyCode()));
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
		EventSystem.submit(new KeyReleased(e.getKeyCode()));
	}
	
	public static boolean isKeyDown(int key) {
		return keys[key];
	}
	
	public static boolean isMouseDown() {
		return mousePressed;
	}
	
	public static int getMouseBtn() {
		return mouseBtn;
	}
	
	public static Vector2 getMousePos() {
		RenderData renderData = DataLocator.getRenderData();
		return getMousePosInWindow().add(renderData.cameraT.position);
	}
	
	public static Vector2 getMousePosInWindow() {
		RenderData renderData = DataLocator.getRenderData();
		return mousePosition.sub(new Vector2(Game.gameImgX, Game.gameImgY)).div(Game.scale).div(renderData.cameraComponent.zoom);
	}
	
	public static Joystick getJoystick(int index) {
		return joysticks.get(index);
	}
	
	public static void update() {
		for(Joystick joystick : joysticks) {
			joystick.update();
		}
	}
	
	public static void init() {
		if(enableJInput) {
			var controllers = ControllerEnvironment.getDefaultEnvironment().getControllers();
			for(Controller controller : controllers) {
				if(controller.getType() == Controller.Type.GAMEPAD) {
					joysticks.add(new Joystick(controller));
				}
			}
		}
		
	}
	
	public static void addControl(String name, Control control) {
		controls.put(name, control);
	}
	
	public static Control getControl(String name) {
		return controls.get(name);
	}
	
	public static boolean isControlDown(String name) {
		return getControl(name).isDown();
	}
	
	public static float getControlValue(String name) {
		return getControl(name).getValue();
	}
	

	@Override
	public void mouseClicked(MouseEvent e) {
		mousePosition = new Vector2(e.getPoint());
		mouseBtn = e.getButton();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		mousePressed = true;
		mousePosition = new Vector2(e.getPoint());
		mouseBtn = e.getButton();
		EventSystem.submit(new MousePressedEvent());
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		mousePressed = false;
		mousePosition = new Vector2(e.getPoint());
		mouseBtn = MouseEvent.NOBUTTON;
		EventSystem.submit(new MouseReleasedEvent());
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		mousePosition = new Vector2(e.getPoint());
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mousePosition = new Vector2(e.getPoint());
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		
		EventSystem.submit(new MouseWheelMoved(e.getWheelRotation()));
	}

}
