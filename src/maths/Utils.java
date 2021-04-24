package maths;

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
}
