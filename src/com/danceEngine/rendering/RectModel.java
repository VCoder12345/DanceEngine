package com.danceEngine.rendering;

import java.awt.Color;
import java.awt.Graphics2D;

import com.danceEngine.ecs.Transform;

public class RectModel extends ColorModel {
	public boolean fill = true;

	public RectModel(Color color) {
		super(color);
		// TODO Auto-generated constructor stub
	}
	
	

	@Override
	protected void render(Graphics2D g2d, int px, int py, int sx, int sy, Transform camT) {
		if(fill)
			g2d.fillRect(px, py, sx, sy);
		else
			g2d.drawRect(px, py, sx, sy);
	}

	

}
