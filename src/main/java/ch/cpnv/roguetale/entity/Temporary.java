package ch.cpnv.roguetale.entity;

public interface Temporary {
	public int getRemainingTime();
	public void setRemainingTime(int remainingTime);
	public void updateRemainingTime(int delta);
	public Boolean isExpired();
}
