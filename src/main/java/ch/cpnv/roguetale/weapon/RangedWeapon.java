package main.java.ch.cpnv.roguetale.weapon;

import main.java.ch.cpnv.roguetale.entity.Direction;

public class RangedWeapon extends Weapon {
	protected int range;

	public RangedWeapon(String name, int damage, int cooldown, int range) {
		super(name, damage, cooldown);
		this.range = range;
	}

	public int getRange() {
		return range;
	}

	public void setRange(int range) {
		this.range = range;
	}

	public void attack(Direction direction) {
		super.attack(direction);
	}
}
