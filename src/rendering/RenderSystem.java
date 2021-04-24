package rendering;

import java.awt.Graphics2D;
import java.util.PriorityQueue;

import camera.CameraComponent;
import debug.Debug;
import ecs.ESystem;
import ecs.Entity;
import ecs.Transform;

public class RenderSystem extends ESystem {
	private Transform cameraTransform;
	private CameraComponent cameraComponent;

	public RenderSystem(Entity camera) {
		super();
		this.cameraTransform = camera.getComponentByType(Transform.class);
		this.cameraComponent = camera.getComponentByType(CameraComponent.class);
	}

	@Override
	public void render(Graphics2D g2d) {
		var queue = filterAndSortEntities();
		while(!queue.isEmpty()) {
			var mtp = queue.poll();
			mtp.model.renderModel(g2d, mtp.transform, cameraTransform, cameraComponent);
		}
		
	}
	
	private PriorityQueue<ModelTransformPair> filterAndSortEntities() {
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
