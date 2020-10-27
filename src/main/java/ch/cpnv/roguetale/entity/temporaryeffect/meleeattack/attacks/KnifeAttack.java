package ch.cpnv.roguetale.entity.temporaryeffect.meleeattack.attacks;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import ch.cpnv.roguetale.entity.temporaryeffect.meleeattack.MeleeAttack;

public class KnifeAttack extends MeleeAttack {
	private static final String DEFAULT_PATH = "ch\\cpnv\\roguetale\\images\\effects\\slash.png";
	
	public KnifeAttack(Vector2f position) throws SlickException {
		super(new SpriteSheet(DEFAULT_PATH, 241, 401, 0), position, 1000);
	}
}
