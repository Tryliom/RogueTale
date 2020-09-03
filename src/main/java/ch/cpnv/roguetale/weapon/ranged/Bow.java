package main.java.ch.cpnv.roguetale.weapon.ranged;

import main.java.ch.cpnv.roguetale.entity.Direction;
import main.java.ch.cpnv.roguetale.weapon.RangedWeapon;

public class Bow extends RangedWeapon {

	public Bow() {
		super("Bow", 1, 500, 10);
	}

	public void attack(Direction direction) {
		// TODO: Attack action
		super.attack(direction);
	}
}
