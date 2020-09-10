package ch.cpnv.roguetale.weapon.ranged;

import ch.cpnv.roguetale.weapon.RangedWeapon;

import org.newdawn.slick.SlickException;

import ch.cpnv.roguetale.entity.character.Character;
import ch.cpnv.roguetale.entity.projectile.Arrow;

public class Bow extends RangedWeapon {

	public Bow() {
		super("Bow", 1, 1000, 10);
	}

	@Override
	public void attack(Character attacker) {		
		if(canAttack()) {
			try {
				projectileController.addProjectile(new Arrow(attacker.getPosition(), attacker.getDirection()));
			} catch (SlickException e) {
				e.printStackTrace();
			}
		}
		
		super.attack(attacker);
	}
}
