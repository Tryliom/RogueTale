package ch.cpnv.roguetale.entity.effect;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.SpriteSheet;

import ch.cpnv.roguetale.entity.DrawableItem;

public class ItemEffect extends DrawableItem {
	protected int remainingTime;
	
	public ItemEffect(SpriteSheet ss, Vector2f position, int remainingTime) {
		super(ss, position);
		this.remainingTime = remainingTime;
	}
	
	public void updateRemainingTime(int delta) {
		this.remainingTime += delta;
	}

	public int getRemainingTime() {
		return remainingTime;
	}

	public void setRemainingTime(int remainingTime) {
		this.remainingTime = remainingTime;
	}
	
	
}
