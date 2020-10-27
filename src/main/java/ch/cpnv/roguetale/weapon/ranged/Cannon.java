package ch.cpnv.roguetale.weapon.ranged;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import ch.cpnv.roguetale.entity.character.Character;
import ch.cpnv.roguetale.entity.projectile.projectiles.Bomb;
import ch.cpnv.roguetale.gui.guis.GameGui;
import ch.cpnv.roguetale.sound.SoundManager;
import ch.cpnv.roguetale.sound.SoundType;
import ch.cpnv.roguetale.weapon.RangedWeapon;

public class Cannon extends RangedWeapon {
	private static String ICON_PATH = "ch\\cpnv\\roguetale\\images\\ui\\icon\\cannon.png";

	public Cannon() throws SlickException {
		super("Cannon", 1, 2000, 200, new Image(ICON_PATH));
	}

	@Override
	public void attack(Character attacker) throws SlickException {		
		if(canAttack()) {
			SoundManager.getInstance().play(SoundType.Arrow, 0.2f);
			GameGui.getProjectileController().addProjectile(new Bomb(attacker, attacker.getDirection(), range, damage));
		}
		
		super.attack(attacker);
	}
}
