package physics.collision;

import java.awt.Color;
import java.awt.Graphics2D;

import ecs.ESystem;
import game.Game;

public class CollGridRenderSystem extends ESystem {

	@Override
	public void render(Graphics2D g2d) {
		CollisionGrid collGrid = Game.getPhysicsInfo().collisionGrid;
		
		for(int y = 0; y < collGrid.anzy; ++y) {
			for(int x = 0; x < collGrid.anzx; ++x) {
				if(collGrid.cells[x][y].solid) {
					int posx = (int) (x * collGrid.cellSize.x);
					int posy = (int) (y * collGrid.cellSize.y);
					
					g2d.setColor(Color.black);
					g2d.fillRect(posx, posy, collGrid.cellSize.xToInt(), collGrid.cellSize.yToInt());
				}
			}
		}
	}
	
}
