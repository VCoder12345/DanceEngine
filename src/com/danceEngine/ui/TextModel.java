package com.danceEngine.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

import com.danceEngine.ecs.Transform;
import com.danceEngine.rendering.ColorModel;

public class TextModel extends UIColorModel {
	public String text;
	public Font font;

	public TextModel(String text, Font font, Color color) {
		super(color);
		this.text = text;
		this.font = font;
	}
	


	@Override
	protected void render(Graphics2D g2d, int px, int py, int sx, int sy, Transform camT) {
		FontMetrics metrics = g2d.getFontMetrics(font);
		int x = px + (sx - metrics.stringWidth(text)) / 2;
		int y = py + (metrics.getAscent() + (sy - (metrics.getAscent() + metrics.getDescent())) / 2);
		
		g2d.setFont(font);
		g2d.drawString(text, x, y);
	}

}
