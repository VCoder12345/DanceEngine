package physics.collision;

import java.awt.Color;
import java.awt.Graphics2D;

import ecs.Transform;
import game.Game;
import rendering.ColorModel;
import rendering.Model;

public class CollGridModel extends ColorModel {
	private CollisionGrid collGrid;

	public CollGridModel(CollisionGrid collGrid, Color color) {
		super(color);
		this.collGrid = collGrid;
	}

	@Override
	protected void render(Graphics2D g2d, int px, int py, int sx, int sy, Transform camT) {
		for(int y = 0; y < collGrid.anzy; ++y) {
			for(int x = 0; x < collGrid.anzx; ++x) {
				if(collGrid.cells[x][y].solid) {
					int posx = (int) (x * collGrid.cellSize.x);
					int posy = (int) (y * collGrid.cellSize.y);
					
					g2d.fillRect(posx, posy, collGrid.cellSize.xToInt(), collGrid.cellSize.yToInt());
				}
			}
		}
	}

	

}
