package com.danceEngine.shape;

import com.danceEngine.ecs.Transform;
import com.danceEngine.utils.Vector2;

public interface Shape {
	public boolean containsPoint(Vector2 p, Transform t);
}
