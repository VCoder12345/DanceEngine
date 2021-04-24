package resources;

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
 	
	private static BufferedImage loadImageFromFile(String path) {
		try {
			BufferedImage img = ImageIO.read(new File(path));
			return img;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
