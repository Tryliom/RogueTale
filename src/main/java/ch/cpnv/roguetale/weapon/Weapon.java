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
		currentCooldown = 0;
	}

	public static void setProjectileController(ProjectileController projectileController) {
		Weapon.projectileController = projectileController;
	}
	
	public String getName() {
		return name;
	}
	
	public int getDamage() {
		return damage;
	}

	public void attack(Character attacker) {
		// The attack is made inside children classes
		if(canAttack()) {
			this.currentCooldown = this.cooldown;
		}
	}
	
	protected boolean canAttack() {
		return currentCooldown <= 0;
	}
	
	public void reduceCooldown(int delta) {
		this.currentCooldown -= delta;
		if (this.currentCooldown < 0)
			this.currentCooldown = 0;
	}
}
