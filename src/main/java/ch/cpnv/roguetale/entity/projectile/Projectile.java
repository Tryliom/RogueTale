package ch.cpnv.roguetale.entity.projectile;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.SpriteSheet;

import ch.cpnv.roguetale.entity.Direction;
import ch.cpnv.roguetale.entity.MovableItem;

public abstract class Projectile extends MovableItem {
	protected int remainingTime;
	
	public Projectile(SpriteSheet ss, Vector2f position, int speed, Direction direction, int lifespan) {
		super(ss, position, speed, direction, true);
		remainingTime = lifespan;
	}
	
	@Override
	public String toString() {
		return "Projectile (" + position.x + ", " + position.y + ")";
	}
	
	@Override
	public void move(int delta) {
		super.move(delta);
		remainingTime -= delta;
	}
	
	@Override
	protected void setImageDirection() {
		switch (this.direction) {
			case UP:
				image.setRotation(270);
				break;
			case DOWN:
				image.setRotation(90);
				break;
			case LEFT:
				image.setRotation(180);
				break;
			case RIGHT:
				break;
			default:
				break;
		}
	}
	
	public Boolean isExpired() {
		return remainingTime <= 0;
	}
}
