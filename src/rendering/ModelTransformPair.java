package rendering;

import ecs.Transform;

public class ModelTransformPair {
	public Model model;
	public Transform transform;
	public ModelTransformPair(Model model, Transform transform) {
		super();
		this.model = model;
		this.transform = transform;
	}

}
