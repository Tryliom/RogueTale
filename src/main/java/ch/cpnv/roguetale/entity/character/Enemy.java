package ch.cpnv.roguetale.entity.character;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;

import ch.cpnv.roguetale.controller.MoneyController;
import ch.cpnv.roguetale.entity.Direction;
import ch.cpnv.roguetale.gui.guis.GameGui;
import ch.cpnv.roguetale.weapon.MeleeWeapon;
import ch.cpnv.roguetale.weapon.RangedWeapon;
import ch.cpnv.roguetale.weapon.Weapon;

public class Enemy extends Character {
	protected int moneyReward;
	
	private static final float PRECISION = 0.35f;
	private int cooldownTargetChange;
	private Character currentOpponent;

	public Enemy(String name, SpriteSheet ss, Vector2f position, int speed, Direction direction, boolean moving,
			Weapon primaryWeapon, Weapon secondaryWeapon, int maxHealth, 
			int moneyReward) throws SlickException {
		super(name, ss, position, speed, direction, moving, primaryWeapon, secondaryWeapon, maxHealth);
		this.moneyReward = moneyReward;
	}
	
	public int getDistanceTo(Vector2f point) {
		float x = this.position.x - point.x;
		float y = this.position.y - point.y;
		
		return (int) Math.round(Math.sqrt(x * x + y * y));
	}
	
	@Override
	public void levelup() throws SlickException {
		super.levelup();
		
		this.bonusSpeed += 0.02;
		
		if (this.level % 3 == 0) {
			this.updateMaxHealth(1);
		}
		
		if (this.level % 5 == 0) {
			if (this.getPrimaryWeapon().canUpgradeTier())
				this.getPrimaryWeapon().upgradeTier();
		}
	}
	
	@Override
	public Character getNearestOpponent() {
		if (cooldownTargetChange <= 0 || currentOpponent == null || currentOpponent.isDead()) {
			cooldownTargetChange = 5000;
			currentOpponent = super.getNearestOpponent();
		}
		
		return currentOpponent;
	}
	
	public void chooseAction() throws SlickException {
		if (this.getNearestOpponent() != null) {
			
			RangedWeapon rangedWeapon = this.getRangedWeapon();
			MeleeWeapon meleeWeapon = this.getMeleeWeapon();
			Weapon weapon = meleeWeapon != null ? meleeWeapon : this.getWeapon();
			
			if (canAttackOpponent()) {
				this.setDirectionToFaceOpponent(false);
				this.setMoving(false);
				
				// RangeWeapon action
				if (rangedWeapon != null) {
					if (rangedWeapon.canShoot())
						rangedWeapon.attack(this);

					if (!rangedWeapon.canAttack() ) {
						if (this.shouldMoveAwayFromOpponent()) {
							this.setDirectionToRunFromOpponent();
							this.setMoving(true);
						} else if (this.shouldMoveTowardOpponent()) {
							this.setDirectionToFaceOpponent(false);
							this.setMoving(true);
						}
					}
				} else if (weapon != null) {
					if (weapon.canAttack()) {
						if (meleeWeapon != null) {
							GameGui.getEnemyController().addCharacterToAttack(this);
						} else
							weapon.attack(this);
					}
				}
			} else if (this.hasRangeWithRangedWeapon() || meleeWeapon != null && this.isFacingOpponent()) {
				this.setDirectionToFaceOpponent(false);
				this.setMoving(true);
			} else {
				this.moveInAttackPosition();
			}
		} else {
			this.setMoving(false);
		}
			
	}
	
	public void update(int delta) throws SlickException {
		super.update(delta);
		
		this.cooldownTargetChange -= delta;
		
		if (this.getRangedWeapon() != null ) {
			if ((this.isAiming() || canAttackOpponent()) && !this.getRangedWeapon().canShoot() && !this.getRangedWeapon().isInCooldown())
				this.getRangedWeapon().aim(delta, this);
			
			if (!canAttackOpponent() && this.getDistanceToMovableItem(GameGui.getPlayerController().getPlayer()) > 400)
				this.getRangedWeapon().attack(this);
		}
	}
	
	protected Boolean shouldMoveTowardOpponent() throws SlickException {
		int rangePercent = getRangePercentReliatiedOpponent();
		
		return rangePercent > 50;
	}
	
	protected Boolean shouldMoveAwayFromOpponent() throws SlickException {
		int rangePercent = getRangePercentReliatiedOpponent();
		
		return rangePercent < 25;
	}
	
	protected int getRangePercentReliatiedOpponent() throws SlickException {
		float distanceToOpponent = this.getRangeToOpponent();
		int range = this.getRangedWeapon().getRange();
		int positionPercent = Math.round(distanceToOpponent/range*100);
		
		return positionPercent;
	}

	protected void setDirectionToFaceOpponent(Boolean lateralShorterDistance) throws SlickException {
		Direction newDirection = this.getDirectionDependingOnOpponent(lateralShorterDistance);
		if (!newDirection.equals(this.direction))
			this.setDirection(newDirection);
	}
	
	protected void setDirectionToRunFromOpponent() throws SlickException {
		Direction newDirection = this.getDirectionDependingOnOpponent(false);

		switch (newDirection) {
			case DOWN:
				newDirection = Direction.UP;
				break;
			case LEFT:
				newDirection = Direction.RIGHT;
				break;
			case RIGHT:
				newDirection = Direction.LEFT;
				break;
			case UP:
				newDirection = Direction.DOWN;
				break;
		}
		
		if (!newDirection.equals(this.direction))
			this.setDirection(newDirection);
	}
	
	protected void moveInAttackPosition() throws SlickException {
		this.setDirectionToFaceOpponent(true);
		this.setMoving(true);
	}
	
	protected Direction getDirectionDependingOnOpponent(Boolean lateralShorterDistance) throws SlickException {
		Character target = this.getNearestOpponent();
		float diffX = this.getPosition().getX() - target.getPosition().getX();
		float diffY = this.getPosition().getY() - target.getPosition().getY();
		
		if (lateralShorterDistance ? Math.abs(diffX) <= Math.abs(diffY) : Math.abs(diffX) >= Math.abs(diffY)) {
			if (diffX <= 0) {
				return Direction.RIGHT;
			} else {
				return Direction.LEFT;
			}
		} else {
			if (diffY <= 0) {
				return Direction.UP;
			} else {
				return Direction.DOWN;
			}
		}
	}
	
	protected Boolean canAttackOpponent() throws SlickException {
		return this.canAttackOpponent(0);
	}
	
	protected Boolean canAttackOpponent(int range) throws SlickException {
		Character target = this.getNearestOpponent();
		if (target == null)
			return false;
		float border = (1-(PRECISION + (this.level/3)/100f))/2;
		
		if (range == 0)
			if (this.getRangedWeapon() != null)
				range = this.getRangedWeapon().getRange();
			else if (this.getMeleeWeapon() != null) {
				range = (int) this.getMeleeWeapon().getHitbox().getWidth();
			}

		Rectangle enRect = new Rectangle(
					this.getPosition().getX() + this.getSprite().getWidth()*border,
					this.getPosition().getY() + this.getSprite().getHeight()*(border+PRECISION), 
					this.getSprite().getWidth()*PRECISION, 
					this.getSprite().getHeight()*PRECISION
				);
		Rectangle pRect = new Rectangle(
				target.getPosition().getX() + target.getSprite().getWidth()*border, 
				target.getPosition().getY() + target.getSprite().getHeight()*(border+PRECISION),
				target.getSprite().getWidth()*PRECISION, 
				target.getSprite().getHeight()*PRECISION
				);
		Direction directionToOpponent = this.getDirectionDependingOnOpponent(false);

		switch (directionToOpponent) {
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
				enRect.setWidth(range);
				break;
			case UP:
				enRect.setY(enRect.getY() + enRect.getHeight());
				enRect.setHeight(range);
				break;
		}
		
		return enRect.intersects(pRect) || enRect.contains(pRect);
	}
	
	public boolean isFacingOpponent() throws SlickException {
		return this.canAttackOpponent(1000);
	}

	protected RangedWeapon getRangedWeapon() {
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
	
	protected Weapon getWeapon() {
		if (this.primaryWeapon != null && !(this.primaryWeapon instanceof MeleeWeapon) && !(this.primaryWeapon instanceof RangedWeapon)) {
			return this.primaryWeapon;
		} else if (this.secondaryWeapon != null && !(this.secondaryWeapon instanceof MeleeWeapon) && !(this.secondaryWeapon instanceof RangedWeapon)) {
			return this.secondaryWeapon;
		} else
			return null;
	}
	
	protected float getRangeToOpponent() throws SlickException {
		Character target = this.getNearestOpponent();
		float diffX = this.getPosition().getX() - target.getPosition().getX();
		float diffY = this.getPosition().getY() - target.getPosition().getY();
		
		if (Math.abs(diffX) > Math.abs(diffY)) {
			return Math.abs(diffX);
		} else {
			return Math.abs(diffY);
		}
	}
	
	public void damage(int damage) {
		super.damage(damage);
		if(isDead()) {
			try {
				die();
			} catch (SlickException e) {
				e.printStackTrace();
			}
		}
	}
	
	public boolean hasRangeWithRangedWeapon() throws SlickException {
		return this.getRangedWeapon() != null && this.getRangeToOpponent() > this.getRangedWeapon().getRange();
	}
	
	protected void die() throws SlickException {		
		GameGui.getEnemyController().removeEnemy(this);
		MoneyController.getInstance().addMoney(moneyReward);
	}
}
