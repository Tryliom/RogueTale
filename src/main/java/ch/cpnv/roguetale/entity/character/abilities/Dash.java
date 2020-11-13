package ch.cpnv.roguetale.entity.character.abilities;

import org.newdawn.slick.SlickException;

import ch.cpnv.roguetale.entity.character.Ability;
import ch.cpnv.roguetale.entity.character.Character;
import ch.cpnv.roguetale.entity.character.states.Phantom;

public class Dash extends Ability {

	public Dash() {
		super("Dash", 5000, 200);
	}
	
	@Override
	public void activate(Character user) {
		if (this.canUse()) {
			super.activate(user);
			user.addState(new Phantom(this.getDuration()));
		}
	}
	
	@Override
	public void onUse(Character user) throws SlickException {
		// Move 10 times more faster
		user.move(10, false);
	}

}
