package physics.collision;

import java.util.ArrayList;

import maths.Vector2;
import tiled.Layer;
import tiled.Tile;
import tiled.TileGrid;
import tiled.Tileset;
import utils.Grid;

public class CollisionGrid extends Grid<CollisionCell> {
	
	
	public CollisionGrid(int anzx, int anzy, Vector2 cellSize) {
		super(anzx, anzy, cellSize);
	}

	@Override
	protected void create() {
		this.cells = new CollisionCell[anzx][anzy];
		for(int y = 0; y < anzy; ++y) {
			for(int x = 0; x < anzx; ++x) {
				this.cells[x][y] = new CollisionCell();
			}
		}
	}
	
	public CollisionGrid(TileGrid tgrid) {
		super(tgrid.anzx, tgrid.anzy, tgrid.cellSize);
		
		for(int y = 0; y < anzy; ++y) {
			for(int x = 0; x < anzx; ++x) {
				Tile tile = tgrid.cells[x][y];
				if(tile.hasProperty("solid")) {
					boolean solid = Boolean.parseBoolean(tile.getProperty("solid"));
					cells[x][y].solid = solid;
				}
			}
		}
	}
	
	public CollisionGrid(ArrayList<Layer> layers, Tileset ts) {
		super(layers.get(0).width ,layers.get(0).height, new Vector2(ts.tilewidth, ts.tileheight));
		for(int y = 0; y < anzy; ++y) {
			for(int x = 0; x < anzx; ++x) {
				boolean solid = false;
				for(int i = 0; i < layers.size(); ++i) {
					Tile tile = ts.tiles[layers.get(i).tileIndices[x][y]];
					if(tile.hasProperty("solid")) {
						solid |= Boolean.parseBoolean(tile.getProperty("solid"));
					}
				}
				cells[x][y].solid = solid;
			}
		}
	}
	
	
}
