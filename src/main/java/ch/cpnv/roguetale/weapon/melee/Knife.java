package ch.cpnv.roguetale.weapon.melee;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

import ch.cpnv.roguetale.entity.character.Character;
import ch.cpnv.roguetale.entity.temporaryeffect.meleeattack.attacks.KnifeAttack;
import ch.cpnv.roguetale.gui.guis.GameGui;
import ch.cpnv.roguetale.weapon.MeleeWeapon;

public class Knife extends MeleeWeapon {

	public Knife() {
		super("Knife", 2, 500, new Rectangle(0, 0, 1, 1));
	}
	
	@Override
	public void attack(Character attacker) throws SlickException {
		super.attack(attacker);
		// TODO correct position + orientation
		GameGui.getMeleeAttackController().addAttack(
				new KnifeAttack(attacker.getPosition(), this));
	}
}
