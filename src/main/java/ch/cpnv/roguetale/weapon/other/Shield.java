package ch.cpnv.roguetale.weapon.other;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import ch.cpnv.roguetale.entity.character.Character;
import ch.cpnv.roguetale.entity.character.states.buff.CancelProjectiles;
import ch.cpnv.roguetale.entity.character.states.buff.Reflection;
import ch.cpnv.roguetale.weapon.Weapon;

public class Shield extends Weapon {
	private static String ICON_PATH = "ch\\cpnv\\roguetale\\images\\ui\\icon\\knife.png";
	private int luckCancelProjectiles = 30;

	public Shield() throws SlickException {
		super("Bouclier en bois", 0, 1000, 0, 500, new Image(ICON_PATH));
	}
	
	@Override
	public void attack(Character attacker) throws SlickException {		
		if (canAttack() && !this.isChargedShoot()) {
			attacker.addState(new Reflection(500, attacker.getDirection()));
		}
		
		super.attack(attacker);
	}
	
	@Override
	public void aim(int delta, Character user) {
		super.aim(delta, user);
		user.addState(new CancelProjectiles(delta, this.luckCancelProjectiles));
	}

	public int getLuckCancelProjectiles() {
		return luckCancelProjectiles;
	}

	public void setLuckCancelProjectiles(int luckCancelProjectiles) {
		this.luckCancelProjectiles = luckCancelProjectiles;
	}
}
