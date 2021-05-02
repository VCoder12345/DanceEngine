package rendering;

import java.awt.Graphics2D;

import camera.CameraComponent;
import ecs.Transform;
import game.Game;
import utils.Vector2;

public abstract class Model {
	public boolean optimisation = true;
	public Model() {
	}



	public void renderModel(Graphics2D g2d, Transform t, Transform camTransform, CameraComponent cameraComponent) {
		Vector2 pos = t.position.sub(camTransform.position).mul(cameraComponent.zoom);
		Vector2 size = t.size.mul(cameraComponent.zoom);
		int px = pos.xToInt();
		int py = pos.yToInt();
		int sx = size.xToInt();
		int sy = size.yToInt();
		
		if(optimisation) {
			
			if(px + sx > 0 && px < Game.width
					&& py + sy > 0 && py < Game.height) {
				
				render(g2d, px, py, sx, sy, camTransform);
			}
		}else {
			render(g2d, px, py, sx, sy, camTransform);
		}

		
	}

	
	protected abstract void render(Graphics2D g2d, int px, int py, int sx, int sy, Transform camT);
}
