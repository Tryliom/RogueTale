package ch.cpnv.roguetale.weapon.ranged;

import ch.cpnv.roguetale.weapon.RangedWeapon;

import org.newdawn.slick.SlickException;

import ch.cpnv.roguetale.entity.character.Character;
import ch.cpnv.roguetale.entity.projectile.Arrow;

public class Bow extends RangedWeapon {

	public Bow() {
		super("Bow", 1, 500, 10);
	}

	@Override
	public void attack(Character attacker) {
		// TODO: Attack action
		super.attack(attacker);
		try {
			// TODO we probably need to clone the char position (getPosition should do it)
			projectileController.addProjectile(new Arrow(attacker.getPosition(), attacker.getDirection()));
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
}
