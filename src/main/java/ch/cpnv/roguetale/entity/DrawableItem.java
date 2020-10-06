package ch.cpnv.roguetale.entity;

import java.util.ArrayList;
import java.util.Iterator;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

import ch.cpnv.roguetale.controller.EnemyController;
import ch.cpnv.roguetale.controller.PlayerController;
import ch.cpnv.roguetale.entity.character.Enemy;
import ch.cpnv.roguetale.entity.effect.ItemEffect;
import ch.cpnv.roguetale.main.Main;

public abstract class DrawableItem {
	protected SpriteSheet spritesheet;
	protected Image image;
	protected Vector2f position;
	protected Animation animation;
	protected Animation deathAnimation;
	protected ArrayList<ItemEffect> activeEffects = new ArrayList<ItemEffect>();

	public DrawableItem(SpriteSheet ss, Vector2f position) {
		this.setSpritesheet(ss);
		this.position = position;		
	}
	
	public Vector2f getPosition() {
		// clone of the position
		return new Vector2f(position.x, position.y);
	}
	
	public void setPosition(Vector2f position) {
		this.position = position;
	}

	public SpriteSheet getSpritesheet() {
		return spritesheet;
	}

	public void setSpritesheet(SpriteSheet spritesheet) {
		this.spritesheet = spritesheet;
		this.image = this.spritesheet.getSprite(0, 0);
	}
	
	public int getImageWidth() {
		return image.getWidth();
	}
	
	public int getImageHeight() {
		return image.getHeight();
	}
	
	public float getXLeft() {
		return position.x - getImageWidth() / 2;
	}
	
	public float getXRight() {
		return position.x + getImageWidth() / 2;
	}
	
	public float getYBottom() {
		return position.y - getImageHeight() / 2;
	}
	
	public float getYTop() {
		return position.y + getImageHeight() / 2;
	}
	
	public Image getSprite() {		
		return this.image;
	}
	
	public Animation getAnimation() {
		return animation;
	}

	public void setAnimation(Animation animation) {
		this.animation = animation;
	}

	public String toString() {
		return "DrawableItem (" + position.x + ", " + position.y + ")";
	}
	
	public void draw(Vector2f origin, GameContainer gc, Color filter) {
		// Note that the slick y coordinates go the opposite direction of the usual y axis
		float xPosition = getXLeft() - origin.x;
		float yPosition =  - (getYTop() - origin.y);
		if (isInScreen(gc, origin)) {
			if (this.animation != null) {
				this.animation.draw(xPosition, yPosition, filter);
			} else {
				this.image.draw(xPosition, yPosition, filter);
			}
			
			for (ItemEffect item : this.activeEffects) {
				item.setPosition(this.getPosition());
				item.draw(origin, gc, filter);
			}
		}
	}
	
	public void update(int delta) throws SlickException {
		for (ItemEffect item : this.activeEffects) {
			item.updateRemainingTime(-delta);
		}
		this.removeExpiredEffects();
	}
	
	public void draw(Vector2f origin, GameContainer gc) {
		draw(origin, gc, null);
	}
	
	public Boolean isInScreen(GameContainer gc, Vector2f screenOrigin) {
		// Note that the screen origin is the UP LEFT corner
		return getXRight() >= screenOrigin.x
				&& getXLeft() <= screenOrigin.x + Main.BASE_WIDTH
				&& getYTop() >= screenOrigin.y - Main.BASE_HEIGHT
				&& getYBottom() <= screenOrigin.y;
	}
	
	public Boolean isColliding(DrawableItem collisionCandidate) {
		return getHitbox().intersects(collisionCandidate.getHitbox()) || getHitbox().contains(collisionCandidate.getHitbox()) || collisionCandidate.getHitbox().contains(getHitbox());
	}
	
	public Shape getHitbox() {		
		return new Rectangle(getHitboxXLeft(), getHitboxYBottom(), getHitboxWidth(), getHitboxHeight());
	}
	
	public boolean isCollidingWithAnotherCharacter() throws SlickException {
		boolean isColliding = false;
		for (Enemy en : EnemyController.getInstance().getEnemies()) {
			if (en != this && this.isColliding(en))
				isColliding = true;
		}
		if (this != PlayerController.getInstance().getPlayer() && this.isColliding(PlayerController.getInstance().getPlayer()))
			isColliding = true;
		
		return isColliding;
	}
	
	protected int getHitboxWidth() {
		return image.getWidth();
	}
	
	protected int getHitboxHeight() {
		return image.getWidth();
	}
	
	protected float getHitboxXLeft() {
		return getXLeft() + (getImageWidth() - getHitboxWidth()) / 2;
	}
	
	protected float getHitboxXRight() {
		return getXRight() - (getImageWidth() - getHitboxWidth()) / 2;
	}
	
	protected float getHitboxYBottom() {
		return getYBottom() + (getImageHeight() - getHitboxHeight()) / 2;
	}
	
	protected float getHitboxYTop() {
		return getYTop() - (getImageHeight() - getHitboxHeight()) / 2;
	}
	
	protected void printHitbox() {
		Shape hitbox = this.getHitbox();
		System.out.println(this + "hitbox : (" + hitbox.getMinX() + ", " + hitbox.getMinY() + ") - ( " + hitbox.getMaxX() + ", " + hitbox.getMaxY() + ")");
	}
	
	private void removeExpiredEffects() throws SlickException {
		for(Iterator<ItemEffect> iterator = this.activeEffects.iterator(); iterator.hasNext();) {
			ItemEffect item = iterator.next();
			if (item.getRemainingTime() <= 0) {
				iterator.remove();
			}
		}
	}
}
