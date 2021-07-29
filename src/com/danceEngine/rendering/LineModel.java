package com.danceEngine.rendering;

import java.awt.Color;
import java.awt.Graphics2D;

import com.danceEngine.ecs.Transform;


public class LineModel extends ColorModel {
	private float x2, y2;
	
	public LineModel(Color color, float x2, float y2) {
		super(color);
		this.x2 = x2;
		this.y2 = y2;
	}

	@Override
	protected void render(Graphics2D g2d, int px, int py, int sx, int sy, Transform camT) {
		int ix2 = (int)(x2 - camT.position.x);
		int iy2 = (int)(y2 - camT.position.y);
		
		g2d.drawLine(px, py, ix2, iy2);
	}



	
	

}
