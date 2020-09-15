package ch.cpnv.roguetale.entity.character;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;

import ch.cpnv.roguetale.entity.Direction;
import ch.cpnv.roguetale.weapon.MeleeWeapon;
import ch.cpnv.roguetale.weapon.RangedWeapon;
import ch.cpnv.roguetale.weapon.Weapon;

public class Enemy extends Character {

	public Enemy(SpriteSheet ss, Vector2f position, int speed, Direction direction, boolean moving,
			Weapon primaryWeapon, Weapon secondaryWeapon) {
		super(ss, position, speed, direction, moving, primaryWeapon, secondaryWeapon);
	}
	
	public void act(Player p) {
		this.setMoving(false);
		if (this.isFaceToPlayer(p)) {
			this.getRangedWeapon().attack(this);
		}
	}

	public void moveTowardPlayer(Player p) {
		float diffX = this.getPosition().getX() - p.getPosition().getX();
		float diffY = this.getPosition().getY() - p.getPosition().getY();
		
		if (Math.abs(diffX) > Math.abs(diffY)) {
			if (diffX < 0) {
				this.setDirection(Direction.RIGHT);
			} else {
				this.setDirection(Direction.LEFT);
			}
		} else {
			if (diffY < 0) {
				this.setDirection(Direction.UP);
			} else {
				this.setDirection(Direction.DOWN);
			}
		}
		
		this.setMoving(true);
	}
	
	public Boolean isFaceToPlayer(Player p) {
		Rectangle enRect = new Rectangle(this.getPosition().getX(), this.getPosition().getY(), this.getSprite().getWidth(), this.getSprite().getHeight());
		Rectangle pRect = new Rectangle(p.getPosition().getX(), p.getPosition().getY(), p.getSprite().getWidth(), p.getSprite().getHeight());
		
		switch (this.getDirection()) {
			case DOWN:
				enRect.setY(-1000);
				enRect.setHeight(1000);
				break;
			case LEFT:
				enRect.setX(-1000);
				enRect.setWidth(1000);
				break;
			case RIGHT:
				enRect.setWidth(1000);
				break;
			case UP:
				enRect.setHeight(1000);
				break;
		}

		return enRect.intersects(pRect);
	}
	
	public RangedWeapon getRangedWeapon() {
		if (this.primaryWeapon != null && this.primaryWeapon instanceof RangedWeapon) {
			return (RangedWeapon) this.primaryWeapon;
		} else if (this.secondaryWeapon != null && this.secondaryWeapon instanceof RangedWeapon) {
			return (RangedWeapon) this.secondaryWeapon;
		} else
			return null;
	}
	
	public MeleeWeapon getMeleeWeapon() {
		if (this.primaryWeapon != null && this.primaryWeapon instanceof MeleeWeapon) {
			return (MeleeWeapon) this.primaryWeapon;
		} else if (this.secondaryWeapon != null && this.secondaryWeapon instanceof MeleeWeapon) {
			return (MeleeWeapon) this.secondaryWeapon;
		} else
			return null;
	}
	
	public Boolean isInPlayerRange(Player p) {
		RangedWeapon rangedWeapon = this.getRangedWeapon();
		
		return rangedWeapon != null && rangedWeapon.getRange() > this.getRangeBetweenPlayer(p);
	}
	
	public float getRangeBetweenPlayer(Player p) {
		float diffX = this.getPosition().getX() - p.getPosition().getX();
		float diffY = this.getPosition().getY() - p.getPosition().getY();
		
		if (Math.abs(diffX) > Math.abs(diffY)) {
			return Math.abs(diffX);
		} else {
			return Math.abs(diffY);
		}
	}

}
