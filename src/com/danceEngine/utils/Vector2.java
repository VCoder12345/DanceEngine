package com.danceEngine.utils;

import java.awt.Point;

public class Vector2 {
	public float x, y;
	
	public Vector2() {
		this.x = 0;
		this.y = 0;
	}
	
	public Vector2(Point point) {
		this.x = point.x;
		this.y = point.y;
	}

	public Vector2(float x, float y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	public Vector2(double x, double y) {
		this.x = (float)x;
		this.y = (float)y;
	}
	
	public Vector2(float x) {
		this(x, x);
	}

	public Vector2 rotated(float theta) {
		float cos = (float) Math.cos(theta);
		float sin = (float) Math.sin(theta);
		return new Vector2(cos * x - sin * y, sin * x + cos * y);
	}
	
	public int xToInt() {
		return (int) Math.floor(x);
	}
	
	public int yToInt() {
		return (int)Math.floor(y);
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
	
	public Vector2 mul(Vector2 v) {
		return new Vector2(x * v.x, y * v.y);
	}
	
	public Vector2 div(float s) {
		return new Vector2(x / s, y / s);
	}
	
	public Vector2 cross(Vector2 v) {
		return new Vector2(x * v.y, y * v.x);
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
		float len = length();
		if(len == 0)
			return new Vector2(0, 0);
		return div(len);
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
	
	public void mulE(Vector2 v) {
		x *= v.x;
		y *= v.y;
	}
	
	public void divE(float s) {
		x /= s;
		y /= s;
	}
	
	public void divE(Vector2 v) {
		x /= v.x;
		y /= v.y;
	}
	
	public void normalize() {
		divE(length());
	}
	
	public boolean smaller(Vector2 v) {
		return x < v.x && y < v.y;
	}
	
	public boolean equals(Vector2 v) {
		return x == v.x && y == v.y;
	}
	
	public boolean smallerEquals(Vector2 v) {
		return smaller(v) || equals(v);
	}
	
	public boolean bigger(Vector2 v) {
		return v.smallerEquals(this);
	}
	
	public boolean biggerThan(Vector2 v) {
		return v.smaller(this);
	}
	
	public void limitE(float maxMag) {
		float len = length();
		if(len > maxMag) {
			normalize();
			mulE(maxMag);
		}
	}
	
	public Vector2 limit(float maxMag) {
		float len = length();
		if(len > maxMag) {
			return normalized().mul(maxMag);
		}
		
		return this;
	}
	
	public static float getAngleBetween(Vector2 a, Vector2 b) {
		float cosAngle = a.dot(b) / (a.length() * b.length());
		return (float)Math.acos(cosAngle);
	}
	
	public static Vector2 fromAngle(float angle) {
		return new Vector2(Math.cos(angle), Math.sin(angle));
	}
	
	public static Vector2 parse(String text) {
		String[] vals = text.split(",");
		float x = Float.parseFloat(vals[0]);
		float y = Float.parseFloat(vals[1]);
		return new Vector2(x, y);
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

	public boolean equalsApprox(Vector2 targetPos, float epsilon) {
		if(Math.abs(targetPos.x - x) > epsilon) return false;
		if(Math.abs(targetPos.y - y) > epsilon) return false;
		return true;
	}

	public Vector2 perp() {
		return new Vector2(-y, x);
	}

	public static Vector2 zero() {
		// TODO Auto-generated method stub
		return new Vector2(0, 0);
	}
	
	
}
