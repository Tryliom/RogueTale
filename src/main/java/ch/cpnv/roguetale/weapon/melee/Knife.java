package ch.cpnv.roguetale.weapon.melee;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

import ch.cpnv.roguetale.entity.character.Character;
import ch.cpnv.roguetale.entity.temporaryeffect.meleeattack.attacks.KnifeAttack;
import ch.cpnv.roguetale.gui.guis.GameGui;
import ch.cpnv.roguetale.sound.SoundManager;
import ch.cpnv.roguetale.sound.SoundType;
import ch.cpnv.roguetale.weapon.MeleeWeapon;

public class Knife extends MeleeWeapon {
	private static String ICON_PATH = "ch\\cpnv\\roguetale\\images\\ui\\icon\\knife.png";
	
	public Knife() throws SlickException {
		this(250);
	}
	
	public Knife(int cooldown) throws SlickException {
		super("Couteau", 2, cooldown, new Rectangle(0, 0, 50, 50), 0, 0, new Image(ICON_PATH));
	}
	
	@Override
	public String getDescription() {
		return "Tranche ce qui est devant d'un coup rapide.";
	}

	
	@Override
	public void attack(Character attacker) throws SlickException {
		if(canAttack()) {
			GameGui.getMeleeAttackController().addAttack(
					new KnifeAttack(attacker, this));
			SoundManager.getInstance().play(SoundType.KnifeSlice, 0.2f);
		}
		super.attack(attacker);
	}
}
