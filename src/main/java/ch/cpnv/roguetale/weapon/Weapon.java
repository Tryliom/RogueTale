package ch.cpnv.roguetale.weapon;

import ch.cpnv.roguetale.controller.ProjectileController;
import ch.cpnv.roguetale.entity.character.Character;

public abstract class Weapon {
	protected static ProjectileController projectileController;
	
	protected String name;
	protected int damage;
	// Cooldown in miliseconds
	protected int cooldown;
	protected int currentCooldown;
	
	public Weapon(String name, int damage, int cooldown) {
		super();
		this.name = name;
		this.damage = damage;
		this.cooldown = cooldown;
	}

	public static void setProjectileController(ProjectileController projectileController) {
		Weapon.projectileController = projectileController;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getDamage() {
		return damage;
	}
	public void setDamage(int damage) {
		this.damage = damage;
	}
	public int getCooldown() {
		return cooldown;
	}
	public void setCooldown(int cooldown) {
		this.cooldown = cooldown;
	}
	
	public int getCurrentCooldown() {
		return currentCooldown;
	}

	public void setCurrentCooldown(int currentCooldown) {
		this.currentCooldown = currentCooldown;
	}

	public Boolean canUse() {
		return this.currentCooldown == 0;
	}

	public void attack(Character attacker) {
		// The attack is made inside children classes
		this.currentCooldown = this.cooldown;
	}
	
	public void updateCooldown(int delta) {
		this.currentCooldown -= delta * 1000;
		if (this.currentCooldown < 0)
			this.currentCooldown = 0;
	}
}
