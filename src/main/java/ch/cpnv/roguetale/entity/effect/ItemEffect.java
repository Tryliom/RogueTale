package ch.cpnv.roguetale.entity.effect;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.SpriteSheet;

import ch.cpnv.roguetale.entity.DrawableItem;
import ch.cpnv.roguetale.entity.Temporary;

public class ItemEffect extends DrawableItem implements Temporary {
	protected int remainingTime;
	
	public ItemEffect(SpriteSheet ss, Vector2f position, int remainingTime) {
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
