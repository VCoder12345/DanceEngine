package rendering;

import java.awt.Color;
import java.awt.Graphics2D;

import ecs.Transform;

public class RectModel extends ColorModel {

	public RectModel(Color color) {
		super(color);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void render(Graphics2D g2d, int px, int py, int sx, int sy, Transform camT) {
		g2d.fillRect(px, py, sx, sy);
	}

	

}
