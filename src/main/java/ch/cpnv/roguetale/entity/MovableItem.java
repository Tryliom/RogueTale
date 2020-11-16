package ch.cpnv.roguetale.entity;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public abstract class MovableItem extends DrawableItem {
	protected int speed;
	protected Direction direction;
	protected Boolean isMoving;
	
	public MovableItem(SpriteSheet ss, Vector2f position, int speed, Direction direction, Boolean isMoving) {
		super(ss, position);
		this.setDirection(direction);
		this.speed = speed;
		this.isMoving = isMoving;
	}
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public int getSpeed() {
		return this.speed;
	}
	
	public Direction getDirection() {
		return direction;
	}
	
	public void setDirection(Direction direction) {
		this.direction = direction;
		this.setImageDirection();
	}
	
	public Boolean isMoving() {
		return isMoving;
	}

	public void setMoving(Boolean isMoving) {
		this.isMoving = isMoving;
	}
	
	@Override
	public String toString() {
		return "MovableItem (" + position.x + ", " + position.y + ")";
	}
	
	public void move(int delta, boolean canPush) throws SlickException {
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
	
	protected void setImageDirection() {
		int yPos = 0;
		switch (this.direction) {
			case LEFT:
				yPos = 1;
				break;
			case RIGHT:
				yPos = 2;
				break;
			case UP:
				yPos = 3;
				break;
			case DOWN:
				yPos = 0;
				break;
		}
		
		this.image = this.getSpritesheet().getSprite(0, yPos);
		this.animation = new Animation(this.spritesheet, 0, yPos, 2, yPos, true, 300, true);
	}
	
	public double getDistanceToMovableItem(MovableItem otherItem) {
		Vector2f diff = Vector2f.sub(this.getPosition(), otherItem.getPosition(), null);
        
        return diff.length();
	}
	
}
