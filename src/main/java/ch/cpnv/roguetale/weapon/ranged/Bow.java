package ch.cpnv.roguetale.weapon.ranged;

import org.newdawn.slick.SlickException;

import ch.cpnv.roguetale.entity.character.Character;
import ch.cpnv.roguetale.entity.projectile.projectiles.Arrow;
import ch.cpnv.roguetale.gui.guis.GameGui;
import ch.cpnv.roguetale.sound.SoundManager;
import ch.cpnv.roguetale.sound.SoundType;
import ch.cpnv.roguetale.weapon.RangedWeapon;

public class Bow extends RangedWeapon {

	public Bow() {
		super("Bow", 1, 500, 500);
	}
	
	public Bow(int cooldown) {
		super("Bow", 1, cooldown, 500);
	}

	@Override
	public void attack(Character attacker) throws SlickException {		
		if (canAttack()) {
			SoundManager.getInstance().play(SoundType.Arrow, 0.2f);
			GameGui.getProjectileController().addProjectile(new Arrow(attacker, attacker.getDirection(), range, damage));
		}
		
		super.attack(attacker);
	}
}
