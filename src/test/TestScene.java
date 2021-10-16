package test;

import com.danceEngine.game.Game;
import com.danceEngine.scene.Scene;

public class TestScene extends Scene {

	@Override
	public void prepare() {

	}
	
	public static void main(String[] args) {
		Game game = new Game();
		game.addScene(new TestScene());
		game.loadScene(0);
		game.startGame();
	}

}
