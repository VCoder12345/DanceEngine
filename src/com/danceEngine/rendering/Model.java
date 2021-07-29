package com.danceEngine.rendering;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;

import com.danceEngine.camera.CameraComponent;
import com.danceEngine.ecs.Transform;
import com.danceEngine.game.Game;
import com.danceEngine.utils.Vector2;

public abstract class Model {
	public boolean optimisation = false;
	public float opacity = 1.0f;
	
	public Model() {
	}



	public void renderModel(Graphics2D g2d, Transform t, Transform camTransform, CameraComponent cameraComponent) {
		Vector2 pos = t.position.sub(camTransform.position).mul(cameraComponent.zoom);
		Vector2 size = t.size.mul(cameraComponent.zoom);
		int px = pos.xToInt();
		int py = pos.yToInt();
		int sx = size.xToInt();
		int sy = size.yToInt();
		
		AlphaComposite alcom = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity);
		g2d.setComposite(alcom);
	
		
		g2d.rotate(t.orientation, px + sx / 2, py + sy / 2);
		
		if(optimisation) {
			
			if(px + sx > 0 && px < Game.width
					&& py + sy > 0 && py < Game.height) {
				
				render(g2d, px, py, sx, sy, camTransform);
			}
		}else {
			render(g2d, px, py, sx, sy, camTransform);
		}
		
		g2d.rotate(-t.orientation, px + sx / 2, py + sy / 2);

	}

	
	protected abstract void render(Graphics2D g2d, int px, int py, int sx, int sy, Transform camT);
}
