package com.danceEngine.utils;

import java.util.ArrayList;
import java.util.Arrays;

import com.danceEngine.ecs.Transform;

public class Utils {
	public static float clampf(float x, float min, float max) {
		float nx = x;
		if(nx < min) {
			nx = min;
		}else if(nx > max) {
			nx = max;
		}
		
		return nx;
	}
	
	public static int clampi(int x, int min, int max) {
		int nx = x;
		if(nx < min) {
			nx = min;
		}else if(nx > max) {
			nx = max;
		}
		
		return nx;
	}
	
	public static float lerp(float a, float b, float t) {
		return (1 - t) * a + t * b;
	}
	
	public static Vector2 lerp(Vector2 v1, Vector2 v2, float t) {
		Vector2 nv = new Vector2();
		nv.x = lerp(v1.x, v2.x, t);
		nv.y = lerp(v1.y, v2.y, t);
		
		return nv;
	}
	
	public static boolean rectCircleIntersection(Vector2 rp, Vector2 rs, Vector2 cp, float r) {
		float closestX = clampf(rp.x, rp.x + rs.x, cp.x);
		float closestY = clampf(rp.y, rp.y + rs.y, cp.y);
		
		Vector2 dist = cp.sub(new Vector2(closestX, closestY));
		float distSquared = dist.sqrLength();
		return distSquared < r*r;
	}
	
	public static <T> void addAll(ArrayList<T> list, T[] values) {
		for(T value : values) {
			list.add(value);
		}
	}
	
	public static boolean pointInRect(Vector2 p, Vector2 rp, Vector2 rs) {
		return (p.x > rp.x && p.x < rp.x + rs.x && p.y > rp.y && p.y < rp.y + rs.y);
	}
	
	public static boolean equalsApprox(float x, float y, float epsilon) {
		return Math.abs(x - y) < epsilon;
	}
	
	public static <T> T[] concat(T[] first, T[] second) {
		  T[] result = Arrays.copyOf(first, first.length + second.length);
		  System.arraycopy(second, 0, result, first.length, second.length);
		  return result;
	}
	
	public static boolean transformTransformIntersection(Transform t1, Transform t2) {
		return rectRectIntersection(t1.position, t1.size, t2.position, t2.size);
	}
	
	public static boolean rectRectIntersection(Vector2 p1, Vector2 s1, Vector2 p2, Vector2 s2) {
		return p1.x + s1.x > p2.x && p1.x  < p2.x + s2.x 
				&& p1.y + s1.y > p2.y && p1.y < p2.y + s2.y;
	}
	

}
