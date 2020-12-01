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
	private static final int MIN_CHARGE_TIME = 1000;
	private static final int MAX_CHARGE_TIME = 2000;

	public Cannon() throws SlickException {
		super("Cannon", 1, 500, 200, new Image(ICON_PATH), MIN_CHARGE_TIME, MAX_CHARGE_TIME);
	}
	
	@Override
	public String getDescription() {
		return "Tir simple: Envoie une bombe qui explose au bout d'un moment ou sur une entité\n"
				+ "Tir chargé: Les dégâts de base de la bombe et la vitesse sont doublés";
	}

	@Override
	public void attack(Character attacker) throws SlickException {		
		if(canAttack()) {
			SoundManager.getInstance().play(SoundType.Arrow, 0.2f);
			Bomb bomb = new Bomb(attacker, attacker.getDirection(), range, damage);
			if (this.isChargedShoot()) {
				bomb.setSpeed(bomb.getSpeed()*2);
				bomb.setDamage(bomb.getDamage()*2);
			}
			GameGui.getProjectileController().addProjectile(bomb);
		}
		
		super.attack(attacker);
	}
}
