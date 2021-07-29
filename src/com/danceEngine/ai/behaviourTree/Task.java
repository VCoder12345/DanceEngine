package com.danceEngine.ai.behaviourTree;

import com.danceEngine.ecs.Entity;

public abstract class Task {
	public Blackboard blackboard;
	public Task parent;
	private PreCondition preCondition = null;
	
	public abstract TaskState run(Entity entity, float dt);
	public abstract Task traverse();
	public abstract Task passUp(TaskState state);
	public boolean checkPreCondition() {return preCondition == null ? true : preCondition.isTrue();}

	
	public void setData(Blackboard blackboard, Task parent) {
		this.blackboard = blackboard;
		this.parent = parent;
	}
	
	
	
	public void start(Entity entity) {}
	public void stop(Entity entity) {}
}
