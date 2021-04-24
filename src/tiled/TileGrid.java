package tiled;

import maths.Vector2;
import utils.Grid;

public class TileGrid extends Grid<Tile> {

	public TileGrid(Layer layer, Tileset ts) {
		super(layer.width, layer.height, new Vector2(ts.tilewidth, ts.tileheight));
		
		for(int y = 0; y < anzy; ++y) {
			for(int x = 0; x < anzx; ++x) {
				cells[x][y] = ts.tiles[layer.tileIndices[x][y]];
			}
		}
	}
	

	@Override
	protected void create() {
		this.cells = new Tile[anzx][anzy];
	}

}
