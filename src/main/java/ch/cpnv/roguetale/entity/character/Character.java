package ch.cpnv.roguetale.entity.character;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import ch.cpnv.roguetale.entity.Direction;
import ch.cpnv.roguetale.entity.MovableItem;
import ch.cpnv.roguetale.weapon.Weapon;

public abstract class Character extends MovableItem {
	protected int currentHealth;
	protected int maxHealth;
	protected Weapon primaryWeapon;
	protected Weapon secondaryWeapon;

	public Character(SpriteSheet ss, 
			Vector2f position, 
			int speed, 
			Direction direction, 
			boolean moving, 
			Weapon primaryWeapon, 
			Weapon secondaryWeapon,
			int maxHealth
			) {
		super(ss, position, speed, direction, moving);
		this.primaryWeapon = primaryWeapon;
		this.secondaryWeapon = secondaryWeapon;
		this.maxHealth = maxHealth;
		this.currentHealth = maxHealth;
	}
	
	public void move(int delta) throws SlickException {
		super.move(delta);
		
		// undo the move if there is a collision
		if (isCollidingWithAnotherCharacter()) {
			// We don't want to create an inifinite loop, 
			// so we really don't want to reuse this move
			super.move(delta * -1);
		}
		
	}
	
	public int getCurrentHealth() {
		return currentHealth;
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public void setPrimaryWeapon(Weapon weapon) {
		this.primaryWeapon = weapon;
	}

	public void setSecondaryWeapon(Weapon weapon) {
		this.secondaryWeapon = weapon;
	}
	
	// TODO prevent currentHealth to become higher than maxHealth
	public void updateHealth(int health) throws SlickException {
		this.currentHealth += health;
	}
	
	public void updateMaxHealth(int health) throws SlickException {
		maxHealth += health;
		updateHealth(health);
	}
	
	public Boolean isDead() {
		return this.currentHealth <= 0;
	}
	
	public void primaryAttack() throws SlickException {
		primaryWeapon.attack(this);
	}
	
	public void secondaryAttack() throws SlickException {
		secondaryWeapon.attack(this);
	}
	
	public void reduceCooldown(int delta) {
		if (primaryWeapon != null)
			primaryWeapon.reduceCooldown(delta);
		if (secondaryWeapon != null)
			secondaryWeapon.reduceCooldown(delta);
	}
}
