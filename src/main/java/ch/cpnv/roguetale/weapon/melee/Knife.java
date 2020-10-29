package ch.cpnv.roguetale.weapon.melee;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

import ch.cpnv.roguetale.entity.character.Character;
import ch.cpnv.roguetale.entity.temporaryeffect.meleeattack.attacks.KnifeAttack;
import ch.cpnv.roguetale.gui.guis.GameGui;
import ch.cpnv.roguetale.weapon.MeleeWeapon;

public class Knife extends MeleeWeapon {
	private static String ICON_PATH = "ch\\cpnv\\roguetale\\images\\ui\\icon\\knife.png";
	
	public Knife() throws SlickException {
		super("Knife", 2, 500, new Rectangle(0, 0, 1, 1), new Image(ICON_PATH));
	}
	
	@Override
	public void attack(Character attacker) throws SlickException {
		super.attack(attacker);
		GameGui.getMeleeAttackController().addAttack(
				new KnifeAttack(attacker, this));
	}
}
