package com.danceEngine.transition;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public abstract class Transition {
	public int toScene = -1;
	
	

	public abstract void render(Graphics2D g2d, BufferedImage lastImg);
	public abstract boolean isComplete();
}
