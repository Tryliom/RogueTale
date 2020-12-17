package ch.cpnv.roguetale.entity.temporaryeffect.meleeattack.attacks;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import ch.cpnv.roguetale.entity.Direction;
import ch.cpnv.roguetale.entity.character.Character;
import ch.cpnv.roguetale.entity.temporaryeffect.meleeattack.MeleeAttack;
import ch.cpnv.roguetale.weapon.melee.Knife;

public class KnifeAttack extends MeleeAttack {
	static protected final int IMAGE_WIDTH = 32;
	static protected final int IMAGE_HEIGHT = 64;
	
	private static final String DEFAULT_PATH = "ch\\cpnv\\roguetale\\images\\effects\\slash.png";
	
	public KnifeAttack(Vector2f position, Direction direction, Knife knife, Character attacker) throws SlickException {
		super(new SpriteSheet(DEFAULT_PATH, 241, 401, 0), position, direction, 100, knife, attacker);
		this.image = this.image.getScaledCopy(IMAGE_WIDTH, IMAGE_HEIGHT);
		this.setImageDirection();
	}
	
	public KnifeAttack(Character attacker, Knife knife) throws SlickException {
		this(attacker.getPosition(), attacker.getDirection(), knife, attacker);
		setPositionFromCharacter(attacker);
	}
	
	@Override
	public int getHitboxWidth() {
		switch(direction) {
		case UP:
		case DOWN:
			return IMAGE_HEIGHT;
		case RIGHT:
		case LEFT:
		default:
			return IMAGE_WIDTH;
		}
	}
	
	@Override
	public int getHitboxHeight() {
		switch(direction) {
		case UP:
		case DOWN:
			return IMAGE_WIDTH;
		case RIGHT:
		case LEFT:
		default:
			return IMAGE_HEIGHT;
		}
	}
}
