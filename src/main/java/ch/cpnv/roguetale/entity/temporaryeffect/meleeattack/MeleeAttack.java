package ch.cpnv.roguetale.entity.temporaryeffect.meleeattack;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.SpriteSheet;

import ch.cpnv.roguetale.entity.character.Character;
import ch.cpnv.roguetale.entity.temporaryeffect.TemporaryEffect;
import ch.cpnv.roguetale.weapon.MeleeWeapon;

public abstract class MeleeAttack extends TemporaryEffect {
	protected MeleeWeapon weapon;
	
	public MeleeAttack(SpriteSheet ss, Vector2f position, int remainingTime, MeleeWeapon weapon) {
		super(ss, position, remainingTime);
		this.weapon = weapon;
	}
	
	public void meetCharacter(Character character) {
		
	}
}
