package com.danceEngine.debug;

import com.danceEngine.game.Game;

public class DebugGame extends Game {
	public DebugGame() {
		super();
		DebugWindow debugWindow = new DebugWindow(800, 800);
	}
}
