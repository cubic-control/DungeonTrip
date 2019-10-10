package com.fuller.DungeonTrip;

public abstract class BaseObject {
	private boolean isContinueous = false;
	
	public abstract void update();
	
	public abstract void render();

	public boolean isContinueous() {
		return isContinueous;
	}

	public void setContinueous(boolean isContinueous) {
		this.isContinueous = isContinueous;
	}

}
