package com.danceEngine.ai.behaviourTree;

import java.util.HashMap;

public class Blackboard {
	private HashMap<String, Object> content = new HashMap<>();
	
	public Object get(String key) {
		return content.get(key);
	}
	
	public void set(String key, Object object) {
		content.put(key, object);
	}
}
