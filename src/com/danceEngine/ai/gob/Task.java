package com.danceEngine.ai.gob;

import com.danceEngine.ecs.Entity;

public interface Task {

	float getGoalChange(Goal goal);
	String getName();
	void applyOn(Goal[] goals);
	void start();
	void run(Entity entity);
	void stop();
}
