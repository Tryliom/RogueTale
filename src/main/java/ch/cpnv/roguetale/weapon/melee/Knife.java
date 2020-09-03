package main.java.ch.cpnv.roguetale.weapon.melee;

import org.newdawn.slick.geom.Shape;

import main.java.ch.cpnv.roguetale.weapon.MeleeWeapon;

public class Knife extends MeleeWeapon {

	public Knife(Shape hitbox) {
		super("Knife", 2, 500, hitbox);
	}

}
