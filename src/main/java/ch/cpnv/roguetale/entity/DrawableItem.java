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
	
	public float getXLeft() {
		return position.x - image.getWidth() / 2;
	}
	
	public float getXRight() {
		return position.x + image.getWidth() / 2;
	}
	
	public float getYBottom() {
		return position.y - image.getHeight() / 2;
	}
	
	public float getYTop() {
		return position.y + image.getHeight() / 2;
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
		if (isInScreen(gc, origin)) {
			if (this.animation != null) {
				this.animation.draw(this.position.x - origin.x - this.image.getWidth() / 2, 
					 - (this.position.y - origin.y + this.image.getHeight() / 2),
					 filter);
			} else {
				this.image.draw(this.position.x - origin.x - this.image.getWidth() / 2, 
					 - (this.position.y - origin.y + this.image.getHeight() / 2),
					 filter);
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
		this.removeExpiredProjectiles();
	}
	
	public void draw(Vector2f origin, GameContainer gc) {
		draw(origin, gc, null);
	}
	
	public Boolean isInScreen(GameContainer gc, Vector2f screenOrigin) {
		// Note that the screen origin is the UP LEFT corner
		return getXRight() >= screenOrigin.x
				&& getXLeft() <= screenOrigin.x + gc.getWidth()
				&& getYTop() >= screenOrigin.y - gc.getHeight()
				&& getYBottom() <= screenOrigin.y;
	}
	
	public Boolean isColliding(DrawableItem collisionCandidate) {
		return getHitbox().intersects(collisionCandidate.getHitbox()) || getHitbox().contains(collisionCandidate.getHitbox()) || collisionCandidate.getHitbox().contains(getHitbox());
	}
	
	public Shape getHitbox() {		
		return new Rectangle(getXLeft(), getYBottom(), image.getWidth(), image.getHeight());
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
	
	protected void printHitbox() {
		Shape hitbox = this.getHitbox();
		System.out.println(this + "hitbox : (" + hitbox.getMinX() + ", " + hitbox.getMinY() + ") - ( " + hitbox.getMaxX() + ", " + hitbox.getMaxY() + ")");
	}
	
	private void removeExpiredProjectiles() throws SlickException {
		// The remove method does not work in a "for(Projectile projectile : projectiles)" loop
		// https://stackoverflow.com/questions/3184883/concurrentmodificationexception-for-arraylist
		for(Iterator<ItemEffect> iterator = this.activeEffects.iterator(); iterator.hasNext();) {
			ItemEffect item = iterator.next();
			if (item.getRemainingTime() <= 0) {
				iterator.remove();
			}
		}
	}
}
