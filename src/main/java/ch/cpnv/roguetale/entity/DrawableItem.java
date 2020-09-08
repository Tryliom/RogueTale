package ch.cpnv.roguetale.entity;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

public class DrawableItem {
	protected SpriteSheet spritesheet;
	protected Image image;
	protected Vector2f position;

	public DrawableItem(SpriteSheet ss, Vector2f position) {
		this.setSpritesheet(ss);
		this.position = position;
	}
	
	public String toString() {
		return "DrawableItem (" + position.x + ", " + position.y + ")";
	}
	
	public void draw(Vector2f origin) {		
		this.image.draw(this.position.x - origin.x - this.image.getHeight() / 2, 
				this.position.y - origin.y - this.image.getWidth() / 2);
	}
	
	public void draw(Vector2f origin, int width, int height) {		
		this.image.draw(this.position.x - origin.x - this.image.getHeight() / 2, 
				this.position.y - origin.y - this.image.getWidth() / 2, 
				width, 
				height);
	}

	public SpriteSheet getSpritesheet() {
		return spritesheet;
	}

	public void setSpritesheet(SpriteSheet spritesheet) {
		this.spritesheet = spritesheet;
		this.image = this.spritesheet.getSprite(0, 0);
	}
	
	public Image getSprite() {		
		return this.image;
	}

	public Vector2f getPosition() {
		return position;
	}

	public void setPosition(Vector2f position) {
		this.position = position;
	}
}
