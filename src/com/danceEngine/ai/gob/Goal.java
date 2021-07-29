package com.danceEngine.ai.gob;

public class Goal {
	private String name;
	public float value;
	

	public Goal(String name, float value) {
		super();
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public float getDiscontentment(float newVal) {
		return newVal;
	}

	@Override
	public String toString() {
		return "Goal [name=" + name + ", value=" + value + "]";
	}
	
	

}
