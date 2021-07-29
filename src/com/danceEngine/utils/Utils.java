package com.danceEngine.utils;

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

}
