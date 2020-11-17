package ch.cpnv.roguetale.weapon;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import ch.cpnv.roguetale.entity.character.Character;

public abstract class Weapon {	
	protected String name;
	protected int damage;
	// Cooldown in milliseconds
	protected int cooldown;
	protected int currentCooldown;
	protected Image icon;
	
	public Weapon(String name, int damage, int cooldown, Image icon) {
		super();
		this.name = name;
		this.damage = damage;
		this.cooldown = cooldown;
		this.icon = icon;
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
}
