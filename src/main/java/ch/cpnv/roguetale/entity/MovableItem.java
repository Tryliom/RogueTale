package ch.cpnv.roguetale.entity;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.SpriteSheet;

public class MovableItem extends DrawableItem {
	protected int speed;
	protected Direction direction;
	protected Boolean moving;
	
	public MovableItem(SpriteSheet ss, Vector2f position, int speed, Direction direction, Boolean moving) {
		super(ss, position);
		this.setDirection(direction);
		this.speed = speed;
		this.moving = moving;
	}
	
	@Override
	public String toString() {
		return "MovableItem (" + position.x + ", " + position.y + ")";
	}
	
	public void move(int delta) {
		float movement = this.speed * delta / 1000f;
		
		switch (this.direction) {
			// Note that y coordinate increase as the item goes down
			case UP:
				position.translate(0, movement);
				break;
			case DOWN:
				position.translate(0, -movement);
				break;
			case LEFT:
				position.translate(-movement, 0);
				break;
			case RIGHT:
				position.translate(movement, 0);
				break;
			default:
				break;
		}
	}
	
	public void setDirection(Direction direction) {
		this.direction = direction;
		this.setImageDirection();
	}
	
	public Direction getDirection() {
		return direction;
	}
	
	protected void setImageDirection() {
		switch (this.direction) {
		case DOWN:
			this.image = this.spritesheet.getSprite(0, 0);
			break;
		case LEFT:
			this.image =  this.spritesheet.getSprite(0, 1);
			break;
		case RIGHT:
			this.image =  this.spritesheet.getSprite(0, 2);
			break;
		case UP:
			this.image =  this.spritesheet.getSprite(0, 3);
			break;
		}
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
}
