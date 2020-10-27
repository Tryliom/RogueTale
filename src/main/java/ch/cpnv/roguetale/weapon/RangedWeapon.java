package ch.cpnv.roguetale.weapon;

import org.newdawn.slick.Image;

public abstract class RangedWeapon extends Weapon {
	protected int range;

	public RangedWeapon(String name, int damage, int cooldown, int range, Image icon) {
		super(name, damage, cooldown, icon);
		this.range = range;
	}

	public int getRange() {
		return range;
	}	
}
