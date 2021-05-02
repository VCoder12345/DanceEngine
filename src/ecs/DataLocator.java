package ecs;

import camera.CameraComponent;
import game.Game;
import rendering.RenderData;

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
