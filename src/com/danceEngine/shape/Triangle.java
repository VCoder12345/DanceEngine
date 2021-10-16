package com.danceEngine.shape;

import com.danceEngine.ecs.Transform;
import com.danceEngine.utils.Utils;
import com.danceEngine.utils.Vector2;

public class Triangle implements Shape {
	public static final int left = 0, right = 1, up = 2, down = 3;
	public Vector2 v1, v2, v3;
	
	
	
	public Triangle(Vector2 v1, Vector2 v2, Vector2 v3) {
		super();
		this.v1 = v1;
		this.v2 = v2;
		this.v3 = v3;
	}



	public Triangle(int dir) {
		switch(dir) {
		case left:
			v1 = new Vector2(0, 0.5f);
			v2 = new Vector2(1, 0);
			v3 = new Vector2(1, 1);
			break;
		case right:
			v1 = new Vector2(0, 0);
			v2 = new Vector2(1, 0.5f);
			v3 = new Vector2(0, 1);
			break;
		case up:
			v1 = new Vector2(0, 1);
			v2 = new Vector2(0.5f, 0);
			v3 = new Vector2(1, 1);
			break;
		case down:
			v1 = new Vector2(0, 0);
			v2 = new Vector2(0.5f, 1);
			v3 = new Vector2(1, 0);
			break;
		}
	}
	
	public Vector2[] getAbsolutePoints(Transform t) {
		Vector2 a = t.position.add(t.size.mul(v1));
		Vector2 b = t.position.add(t.size.mul(v2));
		Vector2 c = t.position.add(t.size.mul(v3));
		return new Vector2[] {a, b, c};
	}
	
	public Vector2[] getAbsolutePoints(float px, float py, float sx, float sy) {
		return getAbsolutePoints(new Transform(px, py, sx, sy));
	}



	@Override
	public boolean containsPoint(Vector2 p, Transform t) {
		Vector2[] points = getAbsolutePoints(t);
		Vector2 a = points[0];
		Vector2 b = points[1];
		Vector2 c = points[2];
		float totalArea = areaOf(a, b, c);
		float areaPAB = areaOf(p, a, b);
		float areaPCB = areaOf(p, c, b);
		float areaPAC = areaOf(p, a, c);
		
		return Utils.equalsApprox(areaPAB + areaPCB + areaPAC, totalArea, 0.1f);
	}
	
	public float area(Transform t) {
		Vector2[] points = getAbsolutePoints(t);
		return areaOf(points);
	}
	
	public static float areaOf(Vector2[] points) {
		return areaOf(points[0], points[1], points[2]);
	}
	
	public static float areaOf(Vector2 a, Vector2 b, Vector2 c) {
		return Math.abs((a.x*(b.y-c.y) + b.x*(c.y-a.y)+
                c.x*(a.y-b.y))/2.0f);
	}
	
	
	
	
}
