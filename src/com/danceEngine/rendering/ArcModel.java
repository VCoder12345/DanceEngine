package com.danceEngine.rendering;

import java.awt.Color;
import java.awt.Graphics2D;

import com.danceEngine.ecs.Transform;

public class ArcModel extends ColorModel {
	private int startAngle;
	private int arcAngle;
	
	

	public ArcModel(float startAngle, float arcAngle, Color color) {
		super(color);
		this.startAngle = (int)Math.toDegrees(startAngle);
		this.arcAngle = (int)Math.toDegrees(arcAngle);
	}


	@Override
	protected void render(Graphics2D g2d, int px, int py, int sx, int sy, Transform camT) {
		g2d.fillArc(px, py, sx, sy, startAngle, arcAngle);
	}

	

}
