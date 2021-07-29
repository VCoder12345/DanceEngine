package com.danceEngine.ai.behaviourTree.editor;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class BTNode extends JPanel {
	public boolean held = false;
	
	public BTNode() {
		setBackground(Color.red);
	}
}
