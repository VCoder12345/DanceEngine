package tiled;

import java.awt.image.BufferedImage;
import java.util.HashMap;

public class Tile {
	public BufferedImage image;
	public HashMap<String, String> properties = new HashMap<>();

	public Tile(BufferedImage image) {
		super();
		this.image = image;
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
