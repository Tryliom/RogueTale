package ch.cpnv.roguetale.weapon;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import ch.cpnv.roguetale.entity.character.Character;

public abstract class Weapon {	
	protected String name;
	protected int damage;
	// Cooldown in miliseconds
	protected int cooldown;
	protected int currentCooldown;
	protected int minChargeTime;
	protected int maxChargeTime;
	protected int currentChargeTime;
	protected Image icon;
	
	public Weapon(String name, int damage, int cooldown, int minChargeTime, int maxChargeTime, Image icon) {
		this.name = name;
		this.damage = damage;
		this.cooldown = cooldown;
		this.icon = icon;
		this.minChargeTime = minChargeTime;
		this.maxChargeTime = maxChargeTime;
		this.currentChargeTime = 0;
		currentCooldown = 0;
	}
	
	public String getName() {
		return name;
	}
	
	public int getDamage() {
		return damage;
	}
	
	public void setDamage(int damage) {
		this.damage = damage;
	}

	public void attack(Character attacker) throws SlickException {
		// The attack is made inside children classes
		if(canAttack()) {
			this.currentCooldown = this.cooldown;
		}
	}
	
	public void reduceCooldown(int delta) {
		this.currentCooldown -= delta;
		if (this.currentCooldown < 0)
			this.currentCooldown = 0;
	}
	
	public boolean canAttack() {
		return currentCooldown <= 0;
	}

	public Image getIcon() {
		return icon;
	}

	public int getCooldown() {
		return cooldown;
	}

	public int getCurrentCooldown() {
		return currentCooldown;
	}
	
	public boolean isInCooldown() {
		return this.currentCooldown != 0;
	}
	
	public boolean canShoot() {
		return this.currentChargeTime > this.minChargeTime;
	}
	
	public boolean isChargedShoot() {
		return this.currentChargeTime > this.maxChargeTime;
	}
	
	public boolean isAiming() {
		return this.currentChargeTime > 0;
	}
	
	// Return 0f to 1f for 0 to 100%
	public float getMinChargePercentCompletion() {
		if (this.currentChargeTime < this.minChargeTime) {
			return (float) this.currentChargeTime / this.minChargeTime;
		} else
			return 1;
	}
	
	// Return 0f to 1f for 0 to 100%
	public float getMaxChargePercentCompletion() {
		if (this.currentChargeTime < this.minChargeTime) {
			return 0;
		} else if (this.currentChargeTime > this.maxChargeTime) {
			return 1;
		} else {
			return (float) (this.currentChargeTime - this.minChargeTime) / (this.maxChargeTime - this.minChargeTime);
		}
	}
	
	public void aim(int delta) {
		this.currentChargeTime += delta;
	}
	
	public void resetAim() {
		this.currentChargeTime = 0;
	}

	public int getMinChargeTime() {
		return minChargeTime;
	}

	public int getMaxChargeTime() {
		return maxChargeTime;
	}

	public int getCurrentChargeTime() {
		return currentChargeTime;
	}
}
