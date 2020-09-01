package ch.cpnv.roguetale.entity;

import org.lwjgl.util.vector.Vector2f;
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
		//this.setPosition(new Vector2f);
	}
	
	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}

}
