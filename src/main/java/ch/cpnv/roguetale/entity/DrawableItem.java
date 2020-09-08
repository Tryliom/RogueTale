package ch.cpnv.roguetale.entity;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.SpriteSheet;

public class DrawableItem {
	protected SpriteSheet spritesheet;
	protected Vector2f position;

	public DrawableItem(SpriteSheet ss, Vector2f position) {
		this.spritesheet = ss;
		this.position = position;
	}

	public SpriteSheet getSpritesheet() {
		return spritesheet;
	}

	public void setSpritesheet(SpriteSheet spritesheet) {
		this.spritesheet = spritesheet;
	}

	public Vector2f getPosition() {
		return position;
	}

	public void setPosition(Vector2f position) {
		this.position = position;
	}
	
	

}
