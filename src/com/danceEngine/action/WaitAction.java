package com.danceEngine.action;

public class WaitAction implements Action {
	private boolean complete = false;
	private long time, startTime = -1;
	
	

	public WaitAction(long time) {
		super();
		this.time = time;
	}

	@Override
	public void execute(float dt) {
		if(startTime == -1) {
			startTime = System.currentTimeMillis();
		}
		
		if(System.currentTimeMillis() - startTime >= time) {
			complete = true;
		}
	}

	@Override
	public boolean isComplete() {
		// TODO Auto-generated method stub
		return complete;
	}

}
