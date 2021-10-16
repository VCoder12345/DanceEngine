package com.danceEngine.rendering;

import java.awt.Color;
import java.awt.Graphics2D;

import com.danceEngine.ecs.Transform;

public class OvalModel extends ColorModel {

	public OvalModel(Color color) {
		super(color);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void render(Graphics2D g2d, int px, int py, int sx, int sy, Transform camT) {
		g2d.fillOval(px, py, sx, sy);
	}

	

}
