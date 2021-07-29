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
	
	public void divE(float s) {
		x /= s;
		y /= s;
	}
	
	public void normalize() {
		divE(length());
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
