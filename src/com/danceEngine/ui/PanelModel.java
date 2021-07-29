package com.danceEngine.ui;

import java.awt.Color;
import java.awt.Graphics2D;

import com.danceEngine.ecs.Transform;

public class PanelModel extends UIColorModel {
	public int roundArcWidth, roundArcHeight;

	public PanelModel(Color color, int roundArcWidth, int roundArcHeight) {
		super(color);
		this.roundArcWidth = roundArcWidth;
		this.roundArcHeight = roundArcHeight;
	}

	@Override
	protected void render(Graphics2D g2d, int px, int py, int sx, int sy, Transform camT) {
		g2d.fillRoundRect(px, py, sx, sy, roundArcWidth, roundArcHeight);
	}

}
