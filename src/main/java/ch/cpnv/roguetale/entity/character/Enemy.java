package ch.cpnv.roguetale.entity.character;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;

import ch.cpnv.roguetale.controller.PlayerController;
import ch.cpnv.roguetale.entity.Direction;
import ch.cpnv.roguetale.weapon.MeleeWeapon;
import ch.cpnv.roguetale.weapon.RangedWeapon;
import ch.cpnv.roguetale.weapon.Weapon;

public class Enemy extends Character {

	public Enemy(SpriteSheet ss, Vector2f position, int speed, Direction direction, boolean moving,
			Weapon primaryWeapon, Weapon secondaryWeapon, int maxHealth) {
		super(ss, position, speed, direction, moving, primaryWeapon, secondaryWeapon, speed);
	}
	
	public void chooseAction() throws SlickException {
		this.changeDirectionToFacingPlayer();
		
		if (isFacingPlayer() && isInPlayerRange()) {
			this.setMoving(false);
			this.getRangedWeapon().attack(this);
		} else {
			this.moveTowardPlayer();
		}
	}

	private void changeDirectionToFacingPlayer() throws SlickException {
		Player p = PlayerController.getInstance().getPlayer();
		float diffX = this.getPosition().getX() - p.getPosition().getX();
		float diffY = this.getPosition().getY() - p.getPosition().getY();
		
		if (Math.abs(diffX) >= Math.abs(diffY)) {
			if (diffX <= 0) {
				this.setDirection(Direction.RIGHT);
			} else {
				this.setDirection(Direction.LEFT);
			}
		} else {
			if (diffY <= 0) {
				this.setDirection(Direction.UP);
			} else {
				this.setDirection(Direction.DOWN);
			}
		}
		
	}

	protected void moveTowardPlayer() throws SlickException {
		Player p = PlayerController.getInstance().getPlayer();
		float diffX = this.getPosition().getX() - p.getPosition().getX();
		float diffY = this.getPosition().getY() - p.getPosition().getY();
		
		if (Math.abs(diffX) <= Math.abs(diffY)) {
			if (diffX <= 0) {
				this.setDirection(Direction.RIGHT);
			} else {
				this.setDirection(Direction.LEFT);
			}
		} else {
			if (diffY <= 0) {
				this.setDirection(Direction.UP);
			} else {
				this.setDirection(Direction.DOWN);
			}
		}
		
		this.setMoving(true);
	}
	
	protected Boolean isFacingPlayer() throws SlickException {
		Player p = PlayerController.getInstance().getPlayer();
		float percent_precision = 0.1f;
		float border = (1-percent_precision)/2;
		Rectangle enRect = new Rectangle(
					this.getPosition().getX() + this.getSprite().getWidth()*border, 
					this.getPosition().getY() + this.getSprite().getHeight() - this.getSprite().getHeight()*border, 
					this.getSprite().getWidth()*percent_precision, 
					this.getSprite().getHeight()*percent_precision
				);
		Rectangle pRect = new Rectangle(
					p.getPosition().getX() + p.getSprite().getWidth()*border, 
					p.getPosition().getY() + p.getSprite().getHeight() - p.getSprite().getHeight()*border,  
					p.getSprite().getWidth()*percent_precision, 
					p.getSprite().getHeight()*percent_precision
				);

		switch (this.getDirection()) {
			case DOWN:
				enRect.setY(enRect.getY()-1000);
				enRect.setHeight(1000);
				break;
			case LEFT:
				enRect.setX(enRect.getX()-1000);
				enRect.setWidth(1000 + enRect.getWidth());
				break;
			case RIGHT:
				enRect.setX(enRect.getX() + enRect.getWidth());
				enRect.setWidth(1000);
				break;
			case UP:
				enRect.setY(enRect.getY() + enRect.getHeight());
				enRect.setHeight(1000);
				break;
		}

		return enRect.intersects(pRect) || enRect.contains(pRect);
	}
	
	protected RangedWeapon getRangedWeapon() {
		if (this.primaryWeapon != null && this.primaryWeapon instanceof RangedWeapon) {
			return (RangedWeapon) this.primaryWeapon;
		} else if (this.secondaryWeapon != null && this.secondaryWeapon instanceof RangedWeapon) {
			return (RangedWeapon) this.secondaryWeapon;
		} else
			return null;
	}
	
	protected MeleeWeapon getMeleeWeapon() {
		if (this.primaryWeapon != null && this.primaryWeapon instanceof MeleeWeapon) {
			return (MeleeWeapon) this.primaryWeapon;
		} else if (this.secondaryWeapon != null && this.secondaryWeapon instanceof MeleeWeapon) {
			return (MeleeWeapon) this.secondaryWeapon;
		} else
			return null;
	}
	
	protected Boolean isInPlayerRange() throws SlickException {
		
		RangedWeapon rangedWeapon = this.getRangedWeapon();
		
		return rangedWeapon != null && rangedWeapon.getRange() > this.getRangeToPlayer();
	}
	
	protected float getRangeToPlayer() throws SlickException {
		Player p = PlayerController.getInstance().getPlayer();
		float diffX = this.getPosition().getX() - p.getPosition().getX();
		float diffY = this.getPosition().getY() - p.getPosition().getY();
		
		if (Math.abs(diffX) > Math.abs(diffY)) {
			return Math.abs(diffX);
		} else {
			return Math.abs(diffY);
		}
	}

}
