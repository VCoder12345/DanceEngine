package com.danceEngine.action;

public interface Action {
	public void execute(float dt);
	public boolean isComplete();
	public default void reset() {}
	public default void end() {}
	public default void start() {}
}
