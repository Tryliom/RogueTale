package ch.cpnv.roguetale.entity;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

public abstract class Projectile extends MovableItem {
	protected int remaining_time;
	
	public Projectile(SpriteSheet ss, Vector2f position, int speed, Direction direction, int lifespan) {
		super(ss, position, speed, direction, true);
		remaining_time = lifespan;
	}
	
	@Override
	protected void setImageDirection() {
		switch (this.direction) {
			case UP:
				System.out.println("Projectile direction : UP");
				image.setRotation(90);
				System.out.println(image.getRotation());
				
				break;
			case DOWN:
				image.setRotation(270);
				break;
			case LEFT:
				image.setRotation(180);
				break;
			case RIGHT:
				break;
			default:
				break;
		}
		System.out.println("Image Direction set - Projectile");
	}
}
