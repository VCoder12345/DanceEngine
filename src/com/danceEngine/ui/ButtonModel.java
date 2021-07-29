package com.danceEngine.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import com.danceEngine.ecs.Transform;

public class ButtonModel extends TextModel {
	public Color textColor;
	public int roundArcWidth, roundArcHeight;

	public ButtonModel(String text, Font font, Color color, Color textColor, int roundArcWidth, int roundArcHeight) {
		super(text, font, color);
		this.textColor = textColor;
		this.roundArcWidth = roundArcWidth;
		this.roundArcHeight = roundArcHeight;
	}
	
	public ButtonModel(String text, Font font, Color textColor, int roundArcWidth, int roundArcHeight) {
		this(text, font, Color.black, textColor, roundArcWidth, roundArcHeight);
	}

	@Override
	protected void render(Graphics2D g2d, int px, int py, int sx, int sy, Transform camT) {
		g2d.fillRoundRect(px, py, sx, sy, roundArcWidth, roundArcHeight);
		g2d.setColor(textColor);
		super.render(g2d, px, py, sx, sy, camT);
	}
	
	

}
