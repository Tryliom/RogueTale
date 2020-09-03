package main.java.ch.cpnv.roguetale.weapon;

import main.java.ch.cpnv.roguetale.entity.Direction;

public class Weapon {
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

	public void attack(Direction direction) {
		// The attack is made inside children classes
		this.currentCooldown = this.cooldown;
	}
	
	public void updateCooldown(int delta) {
		this.currentCooldown -= delta * 1000;
		if (this.currentCooldown < 0)
			this.currentCooldown = 0;
	}
}
