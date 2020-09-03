package main.java.ch.cpnv.roguetale.weapon;

import org.newdawn.slick.geom.Shape;

import main.java.ch.cpnv.roguetale.entity.Direction;

public class MeleeWeapon extends Weapon {
	protected Shape hitbox;

	public MeleeWeapon(String name, int damage, int cooldown, Shape hitbox) {
		super(name, damage, cooldown);
		this.hitbox = hitbox;
	}

	public void attack(Direction direction) {
		super.attack(direction);
	}
}
