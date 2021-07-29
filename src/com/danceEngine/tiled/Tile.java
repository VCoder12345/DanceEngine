package com.danceEngine.tiled;

import java.awt.image.BufferedImage;
import java.util.HashMap;

public class Tile {
	public int id;
	public BufferedImage image;
	public HashMap<String, String> properties = new HashMap<>();

	public Tile(BufferedImage image, int id) {
		super();
		this.image = image;
		this.id = id;
	}
	
	public Tile() {
		
	}
	
	@Override
	public String toString() {
		return "Tile(" + properties + ")";
	}
	
	public void addProperty(String name, String value) {
		properties.put(name, value);
	}
	
	public boolean hasProperty(String name) {
		return properties.containsKey(name);
	}
	
	public String getProperty(String name) {
		return properties.get(name);
	}
}
