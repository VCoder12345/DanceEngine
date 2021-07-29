package com.danceEngine.rendering;

import java.awt.Graphics2D;
import java.util.PriorityQueue;

import com.danceEngine.camera.CameraComponent;
import com.danceEngine.debug.Debug;
import com.danceEngine.ecs.DataLocator;
import com.danceEngine.ecs.ESystem;
import com.danceEngine.ecs.Entity;
import com.danceEngine.ecs.Transform;
import com.danceEngine.game.Game;

public class RenderSystem extends ESystem {

	public RenderSystem() {
		
	}

	@Override
	public void render(Graphics2D g2d) {
		RenderData data = DataLocator.getRenderData();
		var queue = filterAndSortEntities();
		g2d.rotate(-data.cameraT.orientation, Game.width / 2, Game.height / 2);
		while(!queue.isEmpty()) {
			var mtp = queue.poll();			
			mtp.model.renderModel(g2d, mtp.transform, data.cameraT, data.cameraComponent);
		}
		g2d.rotate(data.cameraT.orientation, Game.width / 2, Game.height / 2);
		
	}
	
	protected PriorityQueue<ModelTransformPair> filterAndSortEntities() {
		PriorityQueue<ModelTransformPair> queue = new PriorityQueue<>(new MTPComparator());
		for(Entity entity : getEntitiesWithTypes(Renderer.class, Transform.class)) {
			Renderer r = entity.getComponentByType(Renderer.class);
			Transform t = entity.getComponentByType(Transform.class);
			
			queue.add(new ModelTransformPair(r.model, t));
		}
		
		queue.addAll(Debug.getModels());
		
		return queue;
	}
	
}
