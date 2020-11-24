package ch.cpnv.roguetale.weapon.other;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import ch.cpnv.roguetale.entity.character.Character;
import ch.cpnv.roguetale.entity.character.states.buff.CancelProjectiles;
import ch.cpnv.roguetale.entity.character.states.buff.Reflection;
import ch.cpnv.roguetale.entity.temporaryeffect.itemeffect.effects.ActiveShield;
import ch.cpnv.roguetale.weapon.Weapon;

public class Shield extends Weapon {
	private static String ICON_PATH = "ch\\cpnv\\roguetale\\images\\ui\\icon\\shield.png";
	private int luckCancelProjectiles = 30;

	public Shield() throws SlickException {
		super("Bouclier en bronze", 0, 1000, 0, 500, new Image(ICON_PATH));
	}
	
	@Override
	public void attack(Character attacker) throws SlickException {		
		if (canAttack() && !this.isChargedShoot()) {
			attacker.addState(new Reflection(500, attacker.getDirection()));
			this.addActiveShieldEffect(500, attacker);
		}
		
		super.attack(attacker);
	}
	
	@Override
	public void aim(int delta, Character user) throws SlickException {
		if (!isInCooldown()) {
			user.addState(new CancelProjectiles(delta, this.luckCancelProjectiles));
			this.addActiveShieldEffect(delta, user);
		}
		
		super.aim(delta, user);
	}
	
	public void addActiveShieldEffect(int time, Character user) throws SlickException {
		int offsetX = 0,
			offsetY = 0;

		switch (user.getDirection()) {
			case DOWN:
				offsetY = 20;
				break;
			case LEFT:
				offsetX = 20;
				break;
			case RIGHT:
				offsetX = -17;
				break;
			case UP:
				offsetY = -30;
				offsetX = 6;
				break;
		
		}
		user.addActiveEffect(new ActiveShield(user.getPosition(), time, user.getDirection(), offsetX, offsetY));
	}

	public int getLuckCancelProjectiles() {
		return luckCancelProjectiles;
	}

	public void setLuckCancelProjectiles(int luckCancelProjectiles) {
		this.luckCancelProjectiles = luckCancelProjectiles;
	}
}
