package audio;

import java.util.HashMap;

public class AudioPlayer {
	private static HashMap<String, Sound> sounds = new HashMap<>();
	
	public static void playSound(String filePath, String name, boolean loop) {
		Sound sound = new Sound(filePath, loop);
		sounds.put(name, sound);
		sound.start();
	}
	
	public static void playSound(String filePath, String name) {
		playSound(filePath, name, false);
	}
	
	public static void stopSound(String name) {
		sounds.get(name).stop();
	}
}
