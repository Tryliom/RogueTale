package ch.cpnv.roguetale.entity;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.SpriteSheet;

public abstract class TemporaryEffect extends DrawableItem implements Temporary {

	protected int remainingTime;
	
	public TemporaryEffect(SpriteSheet ss, Vector2f position, int remainingTime) {
		super(ss, position);
		this.remainingTime = remainingTime;
	}
	
	@Override
	public int getRemainingTime() {
		return remainingTime;
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
		return remainingTime <= 0;
	}
	
}
