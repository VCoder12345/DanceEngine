package com.danceEngine.ecs;

public class EntityTag {
	private static int lastExp = 0;
	
	public static int getUniqueId() {
		lastExp++;
		return (int) Math.pow(2, lastExp);
	}
}
