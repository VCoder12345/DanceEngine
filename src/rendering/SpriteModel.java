package rendering;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import ecs.Transform;

public class SpriteModel extends Model {
	public BufferedImage sprite;

	public SpriteModel(BufferedImage sprite) {
		this.sprite = sprite;
	}

	@Override
	protected void render(Graphics2D g2d, int px, int py, int sx, int sy, Transform camT) {
		g2d.drawImage(sprite, px, py, sx, sy, null);
	}

	

}
