package ch.cpnv.roguetale.entity.character;

import ch.cpnv.roguetale.entity.temporaryeffect.Temporary;

public class State implements Temporary {
	private String name;
	private int remainingTime;
	
	protected State(String name, int remainingTime) {
		this.name = name;
		this.remainingTime = remainingTime;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public void update(int delta) {
		this.updateRemainingTime(-delta);
	}

	@Override
	public int getRemainingTime() {
		return this.remainingTime;
	}

	@Override
	public void setRemainingTime(int remainingTime) {
		this.remainingTime = remainingTime;
		
	}

	@Override
	public void updateRemainingTime(int delta) {
		remainingTime += delta;
	}

	@Override
	public Boolean isExpired() {
		return this.remainingTime <= 0;
	}

}
