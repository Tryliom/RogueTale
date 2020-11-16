package ch.cpnv.roguetale.weapon.ranged;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import ch.cpnv.roguetale.entity.character.Character;
import ch.cpnv.roguetale.entity.projectile.projectiles.Arrow;
import ch.cpnv.roguetale.gui.guis.GameGui;
import ch.cpnv.roguetale.sound.SoundManager;
import ch.cpnv.roguetale.sound.SoundType;
import ch.cpnv.roguetale.weapon.RangedWeapon;

public class Bow extends RangedWeapon {
	private static final String ICON_PATH = "ch\\cpnv\\roguetale\\images\\ui\\icon\\bow.png";
	private static final int MIN_CHARGE_TIME = 500;
	private static final int MAX_CHARGE_TIME = 1000;

	public Bow() throws SlickException {
		super("Arc", 1, 300, 500, new Image(ICON_PATH), MIN_CHARGE_TIME, MAX_CHARGE_TIME);
	}
	
	public Bow(int cooldown) throws SlickException {
		super("Arc", 1, cooldown, 500, new Image(ICON_PATH), MIN_CHARGE_TIME, MAX_CHARGE_TIME);
	}

	public void attack(Character attacker) throws SlickException {		
		if (canAttack()) {
			SoundManager.getInstance().play(SoundType.Arrow, 0.2f);
			Arrow arrow = new Arrow(attacker, attacker.getDirection(), range, damage);
			if (this.isChargedShoot()) {
				arrow.setDamage(arrow.getDamage()*2);
				arrow.setSpeed(arrow.getSpeed()*2);
			}
			GameGui.getProjectileController().addProjectile(arrow);
		}
		
		super.attack(attacker);
	}
}
