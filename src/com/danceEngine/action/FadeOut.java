package com.danceEngine.action;

import com.danceEngine.ecs.Entity;
import com.danceEngine.ecs.Transform;
import com.danceEngine.rendering.Model;
import com.danceEngine.rendering.Renderer;
import com.danceEngine.utils.Utils;
import com.danceEngine.utils.Vector2;

public class FadeOut implements Action {
	private Model model;
	private float time;
	private float timer = 0;
	
	public FadeOut(Entity e, long time) {
		this.model = e.getComponentByType(Renderer.class).model;
		this.time = time;
	}
	

	@Override
	public void execute(float dt) {
		timer += dt / time;
		model.opacity = Utils.lerp(1, 0, timer);
		model.opacity = Utils.clampf(model.opacity, 0, 1);
	}

	@Override
	public boolean isComplete() {
		// TODO Auto-generated method stub
		return timer >= 1.0f;
	}

}
