package editor.node;

import java.awt.Graphics2D;

public abstract class Node {
	public int x, y, z;
	public int w, h;
	
	
	
	public Node(int x, int y, int z, int w, int h) {
		super();
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}

	public boolean isInside(int px, int py) {
		return px > x && px < x + w && py > y && py < y + h;
	}

	public abstract void draw(Graphics2D g2d);
}
