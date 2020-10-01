package ch.cpnv.roguetale.entity.character;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;

import ch.cpnv.roguetale.controller.EnemyController;
import ch.cpnv.roguetale.controller.PickupableItemController;
import ch.cpnv.roguetale.controller.PlayerController;
import ch.cpnv.roguetale.entity.Direction;
import ch.cpnv.roguetale.entity.pickupableitem.PickupableLifePoint;
import ch.cpnv.roguetale.weapon.MeleeWeapon;
import ch.cpnv.roguetale.weapon.RangedWeapon;
import ch.cpnv.roguetale.weapon.Weapon;

public class Enemy extends Character {
	
	protected double lifepointSpawnProbability = 0.1;

	public Enemy(SpriteSheet ss, Vector2f position, int speed, Direction direction, boolean moving,
			Weapon primaryWeapon, Weapon secondaryWeapon, int maxHealth) {
		super(ss, position, speed, direction, moving, primaryWeapon, secondaryWeapon, maxHealth);
	}
	
	public void chooseAction() throws SlickException {
		this.facePlayer();
		
		if (canAttackPlayer()) {
			this.setMoving(false);
			this.getRangedWeapon().attack(this);
		} else {
			this.moveInAttackPosition();
		}
	}

	private void facePlayer() throws SlickException {
		this.changeDirectionDependingOnPlayer(false);
	}

	protected void moveInAttackPosition() throws SlickException {
		this.changeDirectionDependingOnPlayer(true);
		this.setMoving(true);
	}
	
	protected void changeDirectionDependingOnPlayer(Boolean lateralShorterDistance) throws SlickException {
		Player p = PlayerController.getInstance().getPlayer();
		float diffX = this.getPosition().getX() - p.getPosition().getX();
		float diffY = this.getPosition().getY() - p.getPosition().getY();
		
		if (lateralShorterDistance ? Math.abs(diffX) <= Math.abs(diffY) : Math.abs(diffX) >= Math.abs(diffY)) {
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
	
	protected Boolean canAttackPlayer() throws SlickException {
		Player p = PlayerController.getInstance().getPlayer();
		float percent_precision = 0.1f;
		float border = (1-percent_precision)/2;
		float range = this.getRangedWeapon() != null ? this.getRangedWeapon().getRange() : 0;
		Rectangle enRect = new Rectangle(
					this.getPosition().getX() + this.getSprite().getWidth()*border, 
					this.getPosition().getY() + this.getSprite().getHeight()*(border+percent_precision), 
					this.getSprite().getWidth()*percent_precision, 
					this.getSprite().getHeight()*percent_precision
				);
		Rectangle pRect = new Rectangle(
					p.getPosition().getX() + p.getSprite().getWidth()*border, 
					p.getPosition().getY() + p.getSprite().getHeight()*(border+percent_precision),
					p.getSprite().getWidth()*percent_precision, 
					p.getSprite().getHeight()*percent_precision
				);

		switch (this.getDirection()) {
			case DOWN:
				enRect.setY(enRect.getY()-range);
				enRect.setHeight(range);
				break;
			case LEFT:
				enRect.setX(enRect.getX()-range);
				enRect.setWidth(range + enRect.getWidth());
				break;
			case RIGHT:
				enRect.setX(enRect.getX() + enRect.getWidth());
				enRect.setWidth(1000);
				break;
			case UP:
				enRect.setY(enRect.getY() + enRect.getHeight());
				enRect.setHeight(range);
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
	
	//@Override
	public void updateHealth(int health) throws SlickException {
		super.updateHealth(health);
		if(isDead()) {
			die();
		}
	}
	
	protected void die() throws SlickException {
		double alea = Math.random();
		if (alea < lifepointSpawnProbability) {
			PickupableItemController.getInstance().addPickupableItem(new PickupableLifePoint(position));
		}
		EnemyController.getInstance().removeEnemy(this);
	}

}
