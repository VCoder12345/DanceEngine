package com.danceEngine.ai.gob;

public class GOBPlanner {
	public Task chooseTask(Task[] tasks, Goal[] goals) {
		Task bestTask = null;
		float bestValue = Float.POSITIVE_INFINITY;
		
		for(Task task : tasks) {
			float val = discontentment(task, goals);
			if(val < bestValue) {
				bestValue = val;
				bestTask = task;
			}
		}
		
		return bestTask;
	}
	
	public float discontentment(Task task, Goal[] goals) {
		float disc = 0;
		
		for(Goal goal : goals) {
			float newVal = goal.value + task.getGoalChange(goal);
			disc += goal.getDiscontentment(newVal);
		}
		
		return disc;
	}
}
