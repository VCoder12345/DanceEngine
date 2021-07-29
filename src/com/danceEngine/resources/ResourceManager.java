package com.danceEngine.resources;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class ResourceManager {
	public static HashMap<String, BufferedImage> images = new HashMap<>();
	
	public static BufferedImage getImage(String name) {
		return images.get(name);
	}
	
	public static BufferedImage loadImage(String path, String name) {
		BufferedImage img = loadImageFromFile(path);
		images.put(name, img);
		return img;
	}
	
	public static BufferedImage getLoadImage(String path, String name) {
		if(images.containsKey(name)) {
			return images.get(name);
		}
		
		return loadImage(path, name);
	}
	
	public static void loadSpriteSheet(String path, int spriteWidth, int spriteHeight, String name) {
		BufferedImage image = loadImageFromFile(path);
		int anzx = image.getWidth() / spriteWidth;
		int anzy = image.getHeight() / spriteHeight;
		int id = 0;
		for(int y = 0; y < anzy; ++y) {
			for(int x = 0; x < anzx; ++x) {
				int spx = x * spriteWidth;
				int spy = y * spriteHeight;
				BufferedImage sprite = image.getSubimage(spx, spy, spriteWidth, spriteHeight);
				images.put(name + id, sprite);
				id++;
			}
		}
	}
 	
	private static BufferedImage loadImageFromFile(String path) {
		try {
			BufferedImage img = ImageIO.read(new File(path));
			return img;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("could not read image with path " + path);
			e.printStackTrace();
			return null;
		}
	}
	
	
}
