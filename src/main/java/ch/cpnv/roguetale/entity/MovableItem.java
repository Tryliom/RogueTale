package main.java.ch.cpnv.roguetale.entity;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

public class MovableItem extends DrawableItem {
	protected int speed;
	protected Direction direction;
	
	public MovableItem(SpriteSheet ss, Vector2f position, int speed, Direction direction) {
		super(ss, position);
		this.speed = speed;
		this.direction = direction;
	}
	
	public void move(int delta) {
		Vector2f pos = this.getPosition();
		int movement = this.speed * delta;
		
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
	
	public Image getSprite(int x, int y) {
		return this.spritesheet.getSprite(x, y);
	}

}
