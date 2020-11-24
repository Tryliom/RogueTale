package ch.cpnv.roguetale.weapon;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import ch.cpnv.roguetale.entity.character.Character;

public abstract class RangedWeapon extends Weapon {
	protected int range;
	
	public RangedWeapon(String name, int damage, int cooldown, int range, Image icon, int minChargeTime, int maxChargeTime) {
		super(name, damage, cooldown, minChargeTime, maxChargeTime, icon);
		this.range = range;
	}
	
	public void attack(Character attacker) throws SlickException {		
		if (this.canAttack()) {
			super.attack(attacker);
		}
		
		this.resetAim();
	}
	
	public int getRange() {
		return range;
	}
}
