package com.danceEngine.resources;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class ResourceManager {
	public static HashMap<String, BufferedImage> images = new HashMap<>();
	
	public static BufferedImage getImage(String name) {
		return images.get(name);
	}
	
	public static BufferedImage[] getImages(String...names) {
		BufferedImage[] imgs = new BufferedImage[names.length];
		for(int i = 0; i < imgs.length; ++i) {
			imgs[i] = getImage(names[i]);
		}
		
		return imgs;
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
	
	public static void loadSpriteSheet(String path, String configFile)  {
		BufferedImage image = loadImageFromFile(path);
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File(configFile)));
			
			String line;
			while((line = reader.readLine()) != null) {
				String[] assignParts = line.split(" = ");
				String name = assignParts[0];
				String[] coords = assignParts[1].split(" ");
				int x = Integer.parseInt(coords[0]);
				int y = Integer.parseInt(coords[1]);
				int w = Integer.parseInt(coords[2]);
				int h = Integer.parseInt(coords[3]);

				BufferedImage sprite = image.getSubimage(x, y, w, h);
				
				images.put(name, sprite);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
