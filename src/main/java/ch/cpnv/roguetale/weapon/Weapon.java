package ch.cpnv.roguetale.weapon;

import java.util.ArrayList;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import ch.cpnv.roguetale.entity.character.Character;
import ch.cpnv.roguetale.entity.character.states.debuff.Slowness;

public abstract class Weapon {	
	protected String name;
	protected int damage;
	protected int tier;
	// Cooldown in milliseconds
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
		this.tier = 1;
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
		
		this.resetAim();
	}
	
	public void reduceCooldown(int delta) {
		this.currentCooldown -= delta;
		if (this.currentCooldown < 0)
			this.currentCooldown = 0;
	}
	
	public boolean canAttack() {
		return currentCooldown <= 0 && this.canShoot();
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
		return this.currentChargeTime >= this.minChargeTime;
	}
	
	public boolean isChargedShoot() {
		return this.currentChargeTime >= this.maxChargeTime;
	}
	
	public boolean isAiming() {
		return this.currentChargeTime > 0;
	}
	
	public float getCooldownPercent() {
		float cooldownPercent = (float) getCurrentCooldown() / getCooldown();
		if (cooldownPercent < 0) {
			return 0;
		}
		if (cooldownPercent > 1) {
			return 1;
		}
		return cooldownPercent;
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
	
	public void aim(int delta, Character user) throws SlickException {
		if (!isInCooldown()) {
			user.addState(new Slowness(delta, 50));
			this.currentChargeTime += delta;
		}
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

	public String getDescription() {
		return "";
	}
	
	/**
	 * Override by sub-classes
	 * @return Array of caracteristics shaping for display
	 */
	public ArrayList<String> getCaracteristics() {
		ArrayList<String> list = new ArrayList<String>();
		
		list.add("Tier: "+this.tier+(this.tier == 10 ? " [MAX]" : ""));
		
		if (this instanceof RangedWeapon) {
			RangedWeapon w = (RangedWeapon) this; 
			list.add("Portée: "+w.getRange()+" unitées");
		}
		
		int damage = this.getDamage();
		if (damage != 0)
			list.add("Dégât: "+damage+" coeur" + (damage > 1 ? "s" : ""));
		list.add("Recharge: "+(this.getCooldown() >= 1000 ? "Long" : "Rapide"));
		
		return list;
	}
	
	public void upgradeTier() {
		this.tier++;
		
		if (this.tier%2 == 0 && this.damage != 0) {
			this.damage += 50;
		}
		
		this.cooldown -= this.cooldown * 0.1;
		this.minChargeTime -= this.minChargeTime * 0.1;
		this.maxChargeTime -= this.maxChargeTime * 0.1;
	}

	public int getTier() {
		return tier;
	}
	
	public boolean canUpgradeTier() {
		return this.tier != 10;
	}
	
	public void upgradeTier(int tier) {
		for (int i=0;i<tier;i++) {
			if (this.canUpgradeTier())
				this.upgradeTier();
		}
	}
}
