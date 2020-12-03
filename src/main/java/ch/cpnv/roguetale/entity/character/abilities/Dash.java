package ch.cpnv.roguetale.entity.character.abilities;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import ch.cpnv.roguetale.entity.character.Ability;
import ch.cpnv.roguetale.entity.character.Character;
import ch.cpnv.roguetale.entity.character.states.buff.Invincible;
import ch.cpnv.roguetale.entity.character.states.buff.Phantom;
import ch.cpnv.roguetale.entity.character.states.buff.Speed;

public class Dash extends Ability {
	static protected final String SPRITESHEET_PATH = "ch\\cpnv\\roguetale\\images\\obstacles\\icons.png";

	public Dash() {
		super("Dash", 5000, 200);
		try {
			SpriteSheet spritesheet = new SpriteSheet(SPRITESHEET_PATH, 48, 48);
			icon = spritesheet.getSprite(7, 6);
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void activate(Character user) {
		if (this.canUse()) {
			user.addState(new Phantom(this.getDuration()));
			user.addState(new Invincible(this.getDuration()));
			user.addState(new Speed(this.getDuration(), 10));
		}
		
		super.activate(user);
	}
}
