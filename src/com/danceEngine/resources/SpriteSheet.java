package com.danceEngine.resources;

import java.awt.image.BufferedImage;

public class SpriteSheet {
	private BufferedImage[] sprites;
	
	public BufferedImage get(int i) {
		return sprites[i];
	}
}
