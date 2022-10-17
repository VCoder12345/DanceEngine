package editor.help;

import java.awt.Point;

public class Helper {
	public static boolean intersects(int px, int py, int rx, int ry, int rw, int rh) {
		return px > rx && px < rx + rw && py > ry && py < ry + rh;
	}
	
	public static boolean intersects(int px, int py, Point rp, int rw, int rh) {
		return intersects(px, py, rp.x, rp.y ,rw, rh);
	}
}
