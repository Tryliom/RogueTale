package ch.cpnv.roguetale.weapon.ranged;

import ch.cpnv.roguetale.weapon.RangedWeapon;

import org.newdawn.slick.SlickException;

import ch.cpnv.roguetale.controller.ProjectileController;
import ch.cpnv.roguetale.entity.character.Character;
import ch.cpnv.roguetale.entity.projectile.Arrow;

public class Bow extends RangedWeapon {

	public Bow() {
		super("Bow", 1, 500, 500);
	}

	@Override
	public void attack(Character attacker) {		
		if(canAttack()) {
			try {
				ProjectileController.getInstance().addProjectile(
						new Arrow(attacker.getPosition(), attacker.getDirection(), range, damage));
			} catch (SlickException e) {
				e.printStackTrace();
			}
		}
		
		super.attack(attacker);
	}
}
