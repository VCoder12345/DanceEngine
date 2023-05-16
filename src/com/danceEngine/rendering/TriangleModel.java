package com.danceEngine.rendering;

import java.awt.Color;
import java.awt.Graphics2D;

import com.danceEngine.ecs.Transform;
import com.danceEngine.shape.Triangle;
import com.danceEngine.utils.Vector2;

public class TriangleModel extends ColorModel {
	private Triangle triangle;
	

	public TriangleModel(Triangle triangle, Color color) {
		super(color);
		this.triangle = triangle;
	}
	

	public TriangleModel(int dir, Color color) {
		super(color);
		this.triangle = new Triangle(dir);
	}


	@Override
	protected void render(Graphics2D g2d, int px, int py, int sx, int sy, Transform camT) {
		final int numPoints = 3;
		Vector2[] points = triangle.getAbsolutePoints(px, py, sx, sy);
		int[] xpoints = new int[numPoints];
		int[] ypoints = new int[numPoints];
		for(int i = 0; i < numPoints; ++i) {
			xpoints[i] = points[i].xToInt();
			ypoints[i] = points[i].yToInt();
		}
		
		g2d.fillPolygon(xpoints, ypoints, numPoints);
	}

}
