package ch.cpnv.roguetale.weapon.ranged;

import ch.cpnv.roguetale.weapon.RangedWeapon;
import ch.cpnv.roguetale.entity.character.Character;

public class Bow extends RangedWeapon {

	public Bow() {
		super("Bow", 1, 500, 10);
	}

	@Override
	public void attack(Character attacker) {
		// TODO: Attack action
		super.attack(attacker);
	}
}
