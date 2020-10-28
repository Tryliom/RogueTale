package ch.cpnv.roguetale.weapon;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import ch.cpnv.roguetale.entity.character.Character;

public abstract class RangedWeapon extends Weapon {
	protected int range;
	protected int minChargeTime;
	protected int maxChargeTime;
	protected int currentChargeTime;
	
	public RangedWeapon(String name, int damage, int cooldown, int range, Image icon, int minChargeTime, int maxChargeTime) {
		super(name, damage, cooldown, icon);
		this.range = range;
		this.minChargeTime = minChargeTime;
		this.maxChargeTime = maxChargeTime;
		this.currentChargeTime = 0;
	}
	
	public void attack(Character attacker) throws SlickException {		
		if (this.canAttack()) {
			super.attack(attacker);
		}
		this.resetAim();
	}
	
	public boolean canAttack() {
		return currentCooldown <= 0 && this.canShoot();
	}
	
	public boolean canShoot() {
		return this.currentChargeTime > this.minChargeTime;
	}
	
	public boolean isChargedShoot() {
		return this.currentChargeTime > this.maxChargeTime;
	}
	
	public void aim(int delta) {
		this.currentChargeTime += delta;
	}
	
	public void resetAim() {
		this.currentChargeTime = 0;
	}

	public int getRange() {
		return range;
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
