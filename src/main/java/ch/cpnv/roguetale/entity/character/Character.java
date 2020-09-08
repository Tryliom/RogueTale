package ch.cpnv.roguetale.entity.character;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.SpriteSheet;

import ch.cpnv.roguetale.entity.Direction;
import ch.cpnv.roguetale.entity.MovableItem;
import ch.cpnv.roguetale.weapon.Weapon;

public class Character extends MovableItem {
	protected int currentHealth;
	protected int maxHealth;
	protected Weapon primaryWeapon;
	protected Weapon secondaryWeapon;

	public Character(SpriteSheet ss, Vector2f position, int speed, Direction direction, boolean moving, Weapon primaryWeapon, Weapon secondaryWeapon) {
		super(ss, position, speed, direction, moving);
		this.primaryWeapon = primaryWeapon;
		this.secondaryWeapon = secondaryWeapon;
	}

	public int getCurrentHealth() {
		return currentHealth;
	}

	public void setCurrentHealth(int currentHealth) {
		this.currentHealth = currentHealth;
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}

	public Weapon getPrimaryWeapon() {
		return primaryWeapon;
	}

	public void setPrimaryWeapon(Weapon primaryWeapon) {
		this.primaryWeapon = primaryWeapon;
	}

	public Weapon getSecondaryWeapon() {
		return secondaryWeapon;
	}

	public void setSecondaryWeapon(Weapon secondaryWeapon) {
		this.secondaryWeapon = secondaryWeapon;
	}
	
	public void updateHealth(int health) {
		this.currentHealth += health;
	}
	
	public Boolean isDead() {
		return this.currentHealth == 0;
	}

}
