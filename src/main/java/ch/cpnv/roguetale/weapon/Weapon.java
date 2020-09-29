package ch.cpnv.roguetale.weapon;

import ch.cpnv.roguetale.entity.character.Character;

public abstract class Weapon {	
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
	
	public String getName() {
		return name;
	}
	
	public int getDamage() {
		return damage;
	}
	
	public void setDamage(int damage) {
		this.damage = damage;
	}

	public void attack(Character attacker) {
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
	
	protected boolean canAttack() {
		return currentCooldown <= 0;
	}
}
