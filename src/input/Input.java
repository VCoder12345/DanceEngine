package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import ecs.DataLocator;
import event.EventSystem;
import game.Game;
import rendering.RenderData;
import utils.Vector2;

public class Input implements KeyListener, MouseListener, MouseMotionListener {
	private static boolean[] keys = new boolean[1024];
	private static boolean mousePressed = false;
	private static Vector2 mousePosition = new Vector2();
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}
	
	public static boolean isKeyDown(int key) {
		return keys[key];
	}
	
	public static boolean isMouseDown() {
		return mousePressed;
	}
	
	public static Vector2 getMousePos() {
		RenderData renderData = DataLocator.getRenderData();
		return mousePosition.div(Game.scale).div(renderData.cameraComponent.zoom).add(renderData.cameraT.position);
	}
	

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		mousePressed = true;
		mousePosition = new Vector2(e.getPoint());
		EventSystem.submit(new MousePressedEvent());
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		mousePressed = false;
		mousePosition = new Vector2(e.getPoint());
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mousePosition = new Vector2(e.getPoint());
	}

}
