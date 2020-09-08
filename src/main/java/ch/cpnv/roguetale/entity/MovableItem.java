package ch.cpnv.roguetale.entity;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

public class MovableItem extends DrawableItem {
	protected int speed;
	protected Direction direction;
	protected Boolean moving;
	
	public MovableItem(SpriteSheet ss, Vector2f position, int speed, Direction direction, Boolean moving) {
		super(ss, position);
		this.speed = speed;
		this.direction = direction;
		this.moving = moving;
	}
	
	public void move(int delta) {
		Vector2f pos = this.getPosition();
		float movement = this.speed * delta / 1000f;
		
		switch (this.direction) {
			case UP:
				pos.translate(0, movement);
				break;
			case DOWN:
				pos.translate(0, -movement);
				break;
			case LEFT:
				pos.translate(-movement, 0);
				break;
			case RIGHT:
				pos.translate(movement, 0);
				break;
			default:
				break;
		}
	}
	
	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public Boolean isMoving() {
		return moving;
	}

	public void setMoving(Boolean moving) {
		this.moving = moving;
	}

	public Image getSprite() {
		
		switch (this.direction) {
			case DOWN:
				return this.spritesheet.getSprite(0, 0);
			case LEFT:
				return this.spritesheet.getSprite(0, 1);
			case RIGHT:
				return this.spritesheet.getSprite(0, 2);
			case UP:
				return this.spritesheet.getSprite(0, 3);
		}
		
		return this.spritesheet.getSprite(0, 0);
	}

}
