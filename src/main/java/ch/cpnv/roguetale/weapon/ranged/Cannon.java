package ch.cpnv.roguetale.weapon.ranged;

import org.newdawn.slick.SlickException;

import ch.cpnv.roguetale.controller.ProjectileController;
import ch.cpnv.roguetale.entity.character.Character;
import ch.cpnv.roguetale.entity.projectile.projectiles.Bomb;
import ch.cpnv.roguetale.weapon.RangedWeapon;

public class Cannon extends RangedWeapon {

	public Cannon() {
		super("Cannon", 1, 2000, 400);
	}

	@Override
	public void attack(Character attacker) throws SlickException {		
		if(canAttack()) {
			ProjectileController.getInstance().addProjectile(
					new Bomb(attacker, attacker.getDirection(), range, damage)
				);
		}
		
		super.attack(attacker);
	}
}
