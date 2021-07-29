package com.danceEngine.ecs;

import com.danceEngine.camera.CameraComponent;
import com.danceEngine.game.Game;
import com.danceEngine.rendering.RenderData;

public class DataLocator {
	private static RenderData renderData;
	
	public static void init() {
		Entity camera = new Entity("camera", 0, 0, Game.width, Game.height, 0);
		camera.addComponent(new CameraComponent(1.0f));
		renderData = new RenderData(camera);
	}
	
	public static void provide(RenderData data) {
		renderData = data;
	}
	
	public static RenderData getRenderData() {
		return renderData;
	}
}
