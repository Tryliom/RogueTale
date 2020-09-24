package ch.cpnv.roguetale.weapon.ranged;

import org.newdawn.slick.SlickException;

import ch.cpnv.roguetale.entity.character.Character;
import ch.cpnv.roguetale.weapon.RangedWeapon;

public class Cannon extends RangedWeapon {

	public Cannon() {
		super("Cannon", 1, 1000, 400);
	}

	@Override
	public void attack(Character attacker) throws SlickException {		
		if(canAttack()) {
			
		}
		
		super.attack(attacker);
	}
}
