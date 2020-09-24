package ch.cpnv.roguetale.entity.projectile;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

import ch.cpnv.roguetale.entity.Direction;
import ch.cpnv.roguetale.entity.MovableItem;
import ch.cpnv.roguetale.entity.character.Character;

public abstract class Projectile extends MovableItem {
	protected int damage;
	protected int remainingTime;
	
	public Projectile(SpriteSheet ss, Vector2f position, int speed, Direction direction, int range, int damage) {
		super(ss, position, speed, direction, true);
		remainingTime = range / speed * 1000;
		this.damage = damage;
	}
	
	public Projectile(SpriteSheet ss, Character attacker, int speed, Direction direction, int range, int damage) {
		this(ss, attacker.getPosition(), speed, direction, range, damage);
		setPositionFromCharacter(attacker, direction);
	}
	
	@Override
	public String toString() {
		return "Projectile (" + position.x + ", " + position.y + ")";
	}
	
	@Override
	public void move(int delta) throws SlickException {
		super.move(delta);
		remainingTime -= delta;
	}
	
	@Override
	protected void setImageDirection() {
		switch (this.direction) {
			case UP:
				image.setRotation(270);
				break;
			case DOWN:
				image.setRotation(90);
				break;
			case LEFT:
				image.setRotation(180);
				break;
			case RIGHT:
				break;
			default:
				break;
		}
	}
	
	public void meetCharacter(Character touchedCharacter) {
		touchedCharacter.updateHealth(-damage);
		remainingTime = 0;
	}
	
	public Boolean isExpired() {
		return remainingTime <= 0;
	}
	
	protected void setPositionFromCharacter(Character attacker, Direction projectileDirection) {
		Image attackerSprite = attacker.getSprite();
		
		float margin = 1;
		
		switch(projectileDirection) {
		case RIGHT:
			position.x += (attackerSprite.getWidth() + image.getWidth()) / 2 + margin;
			break;
		case LEFT:
			position.x -= (attackerSprite.getWidth() + image.getWidth()) / 2 + margin;
			break;
		case UP:
			position.y += (attackerSprite.getHeight() + image.getWidth()) / 2 + margin;
			break;
		case DOWN:
			position.y -= (attackerSprite.getHeight() + image.getWidth()) / 2 + margin;
			break;
		}
	}
	
	@Override
	public Shape getHitbox() {
		switch(direction) {
			case UP:
			case DOWN:
				return new Rectangle(position.x -  image.getHeight(), position.y + image.getWidth(), image.getHeight(), image.getWidth());
			case RIGHT:
			case LEFT:
			default:
				return super.getHitbox();
		}
	}
}
