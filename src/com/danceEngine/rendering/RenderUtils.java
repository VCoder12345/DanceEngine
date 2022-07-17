package com.danceEngine.rendering;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

public class RenderUtils {
	public static void drawCenteredText(Graphics2D g2d, int px, int py, int w, int h, String text, Font font, Color color) {
		g2d.setFont(font);
		g2d.setColor(color);
		
		FontMetrics fm = g2d.getFontMetrics();
	    int x = (w - fm.stringWidth(text)) / 2;
	    int y = (fm.getAscent() + (h - (fm.getAscent() + fm.getDescent())) / 2);
	    g2d.drawString(text, px + x, py + y);
	}
}
