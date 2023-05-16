package com.danceEngine.rendering;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

import com.danceEngine.ecs.Transform;

public class RectModel extends ColorModel {
	public boolean fill;
	public float borderSize;

	public RectModel(Color color, boolean fill, float borderSize) {
		super(color);
		this.fill = fill;
		this.borderSize = borderSize;
	}
	
	public RectModel(Color color, boolean fill) {
		this(color, fill, 0.0f);
	}
	
	public RectModel(Color color) {
		this(color, true);
	}
	

	@Override
	protected void render(Graphics2D g2d, int px, int py, int sx, int sy, Transform camT) {
		if(fill)
			g2d.fillRect(px, py, sx, sy);
		else
			g2d.drawRect(px, py, sx, sy);
		
		if(borderSize > 0) {
			g2d.setColor(Color.black);
			g2d.setStroke(new BasicStroke(borderSize));
			
			g2d.drawRect(px, py, sx, sy);
		}
	}

	

}
