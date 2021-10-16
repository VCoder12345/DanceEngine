package com.danceEngine.shape;

import com.danceEngine.ecs.Transform;
import com.danceEngine.utils.Vector2;

public class Rectangle implements Shape {
	private Vector2 relMin, relMax;
	
	
	
	public Rectangle(Vector2 relMin, Vector2 relMax) {
		super();
		this.relMin = relMin;
		this.relMax = relMax;
	}
	
	public Rectangle() {
		this(new Vector2(0, 0), new Vector2(1, 1));
	}

	public Vector2[] getBounds(Transform t) {
		Vector2 min = t.position.add(t.size.mul(relMin));
		Vector2 max = t.position.add(t.size.mul(relMax));
		
		return new Vector2[] {min, max};
	}

	@Override
	public boolean containsPoint(Vector2 p, Transform t) {
		Vector2[] bounds = getBounds(t);
		return bounds[0].smaller(p) && p.smaller(bounds[1]);
	}

}
