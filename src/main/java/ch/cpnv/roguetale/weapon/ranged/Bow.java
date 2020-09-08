package ch.cpnv.roguetale.weapon.ranged;

import ch.cpnv.roguetale.entity.Direction;
import ch.cpnv.roguetale.weapon.RangedWeapon;

public class Bow extends RangedWeapon {

	public Bow() {
		super("Bow", 1, 500, 10);
	}

	public void attack(Direction direction) {
		// TODO: Attack action
		super.attack(direction);
	}
}
