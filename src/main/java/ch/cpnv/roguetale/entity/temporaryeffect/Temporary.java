package ch.cpnv.roguetale.entity.temporaryeffect;

public interface Temporary {
	public int getRemainingTime();
	public void setRemainingTime(int remainingTime);
	public void updateRemainingTime(int delta);
	public Boolean isExpired();
}
