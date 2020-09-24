package ch.cpnv.roguetale.weapon.ranged;

import ch.cpnv.roguetale.weapon.RangedWeapon;

import org.newdawn.slick.SlickException;

import ch.cpnv.roguetale.controller.ProjectileController;
import ch.cpnv.roguetale.entity.character.Character;
import ch.cpnv.roguetale.entity.projectile.projectiles.Arrow;

public class Bow extends RangedWeapon {

	public Bow() {
		super("Bow", 1, 500, 500);
	}

	@Override
	public void attack(Character attacker) throws SlickException {		
		if(canAttack()) {
			ProjectileController.getInstance().addProjectile(
							new Arrow(attacker, attacker.getDirection(), range, damage)
						);
		}
		
		super.attack(attacker);
	}
}
