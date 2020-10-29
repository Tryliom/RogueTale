package ch.cpnv.roguetale.weapon;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;

import ch.cpnv.roguetale.entity.character.Character;

public abstract class MeleeWeapon extends Weapon {
	protected Shape hitbox;

	public MeleeWeapon(String name, int damage, int cooldown, Shape hitbox, Image icon) {
		super(name, damage, cooldown, icon);
		this.hitbox = hitbox;
	}
	
	public void meetCharacter(Character attackedCharacter) throws SlickException {
		attackedCharacter.updateHealth(-damage);
	}
}
