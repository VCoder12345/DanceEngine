package tiled;

import java.awt.image.BufferedImage;
import java.util.HashMap;

public class Tileset {
	public int tilewidth, tileheight;
	public Tile[] tiles;
	
	public Tileset(int tilewidth, int tileheight, Tile[] tiles) {
		super();
		this.tilewidth = tilewidth;
		this.tileheight = tileheight;
		this.tiles = tiles;
	}
	

	
}
