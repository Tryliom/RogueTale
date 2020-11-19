package ch.cpnv.roguetale.entity.character.abilities;

import ch.cpnv.roguetale.entity.character.Ability;
import ch.cpnv.roguetale.entity.character.Character;
import ch.cpnv.roguetale.entity.character.states.Invincible;
import ch.cpnv.roguetale.entity.character.states.Phantom;
import ch.cpnv.roguetale.entity.character.states.Speed;

public class Dash extends Ability {

	public Dash() {
		super("Dash", 5000, 200);
	}
	
	@Override
	public void activate(Character user) {
		if (this.canUse()) {
			user.addState(new Phantom(this.getDuration()));
			user.addState(new Invincible(this.getDuration()));
			user.addState(new Speed(this.getDuration()));
		}
		
		super.activate(user);
	}
}
