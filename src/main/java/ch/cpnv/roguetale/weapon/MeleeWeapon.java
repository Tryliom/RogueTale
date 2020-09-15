package ch.cpnv.roguetale.weapon;

import org.newdawn.slick.geom.Shape;

import ch.cpnv.roguetale.entity.character.Character;

public abstract class MeleeWeapon extends Weapon {
	protected Shape hitbox;

	public MeleeWeapon(String name, int damage, int cooldown, Shape hitbox) {
		super(name, damage, cooldown);
		this.hitbox = hitbox;
	}

	@Override
	public void attack(Character attacker) {
		super.attack(attacker);
	}
}
