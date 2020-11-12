package ch.cpnv.roguetale.entity.projectile;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import ch.cpnv.roguetale.entity.Direction;
import ch.cpnv.roguetale.entity.MovableItem;
import ch.cpnv.roguetale.entity.character.Character;
import ch.cpnv.roguetale.entity.obstacle.Obstacle;
import ch.cpnv.roguetale.entity.temporaryeffect.Temporary;

public abstract class Projectile extends MovableItem implements Temporary {
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
	public void move(int delta, boolean canPush) throws SlickException {
		super.move(delta, canPush);
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
	
	public void onDeath() throws SlickException {}
	
	@Override
	public void draw(Vector2f origin, GameContainer gc) {
		draw(origin, gc, Color.white);
	}
	
	public void meetCharacter(Character touchedCharacter) throws SlickException {
		touchedCharacter.updateHealth(-damage);
		remainingTime = 0;
	}
	
	public void meetObstacle(Obstacle touchedObstacle) {
		remainingTime = 0;
	}
	
	protected void setPositionFromCharacter(Character attacker, Direction projectileDirection) {
		Image attackerSprite = attacker.getSprite();
		
		float margin = 1;
		
		switch(projectileDirection) {
		case RIGHT:
			position.x += (attackerSprite.getWidth() + getImageWidth()) / 2 + margin;
			break;
		case LEFT:
			position.x -= (attackerSprite.getWidth() + getImageWidth()) / 2 + margin;
			break;
		case UP:
			position.y += (attackerSprite.getHeight() + getImageWidth()) / 2 + margin;
			break;
		case DOWN:
			position.y -= (attackerSprite.getHeight() + getImageWidth()) / 2 + margin;
			break;
		}
	}
	
	@Override 
	public int getImageWidth() {
		switch(direction) {
		case UP:
		case DOWN:
			return super.getImageHeight();
		case RIGHT:
		case LEFT:
		default:
			return super.getImageWidth();
		}
	}
	
	@Override 
	public int getImageHeight() {
		switch(direction) {
		case UP:
		case DOWN:
			return super.getImageWidth();
		case RIGHT:
		case LEFT:
		default:
			return super.getImageHeight();
		}
	}
	
	@Override
	public int getRemainingTime() {
		return remainingTime;
	}
	@Override
	public void setRemainingTime(int remainingTime) {
		this.remainingTime = remainingTime;
	}
	@Override
	public void updateRemainingTime(int delta) {
		remainingTime += delta;
	}
	@Override
	public Boolean isExpired() {
		return remainingTime <= 0;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}
}
