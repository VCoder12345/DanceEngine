package maths;

import java.awt.Point;

public class Vector2 {
	public float x, y;
	
	public Vector2() {
		this.x = 0;
		this.y = 0;
	}

	public Vector2(float x, float y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	public int xToInt() {
		return (int)x;
	}
	
	public int yToInt() {
		return (int)y;
	}
	
	public Point toPoint() {
		return new Point(xToInt(), yToInt());
	}
	
	public Vector2 add(Vector2 vec) {
		return new Vector2(x + vec.x, y + vec.y);
	}
	
	public Vector2 sub(Vector2 vec) {
		return new Vector2(x - vec.x, y - vec.y);
	}
	
	public Vector2 mul(float s) {
		return new Vector2(x * s, y * s);
	}
	
	public Vector2 div(float s) {
		return new Vector2(x / s, y / s);
	}
	
	public float sqrLength() {
		return x * x + y * y;
	}
	
	public float length() {
		return (float) Math.sqrt(sqrLength());
	}
	
	public float dot(Vector2 vec) {
		return x * vec.x + y * vec.y;
	}
	
	public Vector2 normalized() {
		return div(length());
	}
	
	public void addE(Vector2 vec) {
		x += vec.x;
		y += vec.y;
	}
	
	public void subE(Vector2 vec) {
		x -= vec.x;
		y -= vec.y;
	}
	
	public void mulE(float s) {
		x *= s;
		y *= s;
	}
	
	public void divE(float s) {
		x /= s;
		y /= s;
	}
	
	public void normalize() {
		divE(length());
	}

	public Vector2 copy() {
		// TODO Auto-generated method stub
		return new Vector2(x, y);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Vector2(" + x + ", " + y + ")";
	}
	
	
}
