package com.danceEngine.ai.behaviourTree.editor;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

public class BTEditor extends JFrame {
	BTNode node;
	
	public BTEditor() {
		setSize(1200, 1000);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("BehaviourTree-Editor");
		setLayout(null);
		
		node = new BTNode();
		node.setBounds(100, 100, 100, 100);
		add(node);
		
		var listener = new DragMouseAdapter();
		addMouseListener(listener);
		addMouseMotionListener(listener);
		
		setVisible(true);
	}
	
	private class DragMouseAdapter extends MouseAdapter {

		@Override
		public void mousePressed(MouseEvent e) {
			super.mousePressed(e);
			if(node.getBounds().contains(e.getPoint())) {
				node.held = true;
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			super.mouseReleased(e);
			node.held = false;
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
			super.mouseMoved(e);
			if(node.held) {
				node.setLocation(e.getX() - node.getWidth() / 2, e.getY() - node.getHeight() / 2);
			}
		}
		
	}
	
	public static void main(String[] args) {
		new BTEditor();
	}
}
