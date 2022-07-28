package com.danceEngine.tiled;

import java.awt.Color;
import java.util.HashMap;

import com.danceEngine.utils.Vector2;

public class GameObject {
	public HashMap<String, String> properties = new HashMap<>();
	private Vector2 position, size;
	public int gid, id;
	public String name, type;
	
	public GameObject(String name, String type, Vector2 position, Vector2 size, int id, int gid) {
		this.name = name;
		this.type = type;
		this.position = position;
		this.size = size;
		this.id = id;
		this.gid = gid;
	}
	
	public Vector2 getPos() {
		return position.copy();
	}
	
	
	public Vector2 getSize() {
		return size.copy();
	}
	
	public void addProperty(String name, String value) {
		properties.put(name, value);
	}
	
	public String getProperty(String name) {
		return properties.get(name);
	}
	
	public float getFloatProperty(String name) {
		return Float.parseFloat(properties.get(name));
	}
	
	public boolean getBoolProperty(String name) {
		return properties.get(name).equals("true");
	}
	
	public int getIntProperty(String name) {
		return Integer.parseInt(properties.get(name));
	}
	
	public Color getColorProperty(String name) {
		String colorText = properties.get(name);
		colorText = "#" + colorText.substring(3);
		Color color = Color.decode(colorText);
		return color;
	}
	
}
