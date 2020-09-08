package ch.cpnv.roguetale.entity;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

public abstract class Projectile extends MovableItem {
	protected int remaining_time;
	
	public Projectile(SpriteSheet ss, Vector2f position, int speed, Direction direction, int lifespan) {
		super(ss, position, speed, direction, false);
		this.remaining_time = lifespan;
	}
	
	public Image getSprite() {
		Image image = this.spritesheet.getSprite(0, 0);
		switch (this.direction) {
		case UP:
			break;
		case DOWN:
			break;
		case LEFT:
			break;
		case RIGHT:
			break;
		default:
			break;
	}
		return image;
	}
}
