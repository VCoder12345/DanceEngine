package editor.node;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteNode extends Node {
	public BufferedImage image;
	
	public SpriteNode(int x, int y, int z, int w, int h, BufferedImage image) {
		super(x, y, z, w, h);
		this.image = image;
	}
	
	public SpriteNode(int x, int y, int z, int w, int h, String imgPath) throws IOException {
		this(x, y, z, w, h, ImageIO.read(new File(imgPath)));
	}
	
	

	@Override
	public void draw(Graphics2D g2d) {
		g2d.drawImage(image, x, y, w, h, null);
	}

}
