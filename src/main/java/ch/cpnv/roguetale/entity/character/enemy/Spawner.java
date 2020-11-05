package ch.cpnv.roguetale.entity.character.enemy;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import ch.cpnv.roguetale.entity.Direction;
import ch.cpnv.roguetale.entity.character.Enemy;
import ch.cpnv.roguetale.sound.SoundManager;
import ch.cpnv.roguetale.sound.SoundType;

public class Spawner extends Enemy {
	private static final int SPEED = 0;
	private static final int MAX_HEALTH = 20;
	private static final String SPRITESHEET_PATH = "ch\\cpnv\\roguetale\\images\\enemy\\robot\\carac.png";
	private static final int  SPRITESHEET_DIMENSIONS = 48;
	private static final int animationLength = 300;

	public Spawner(Vector2f position) throws SlickException {
		super(getSpriteSheet(), 
				position, SPEED, Direction.UP, false, null, null, MAX_HEALTH);
	}
	
	public static SpriteSheet getSpriteSheet() throws SlickException {
		return new SpriteSheet(SPRITESHEET_PATH, SPRITESHEET_DIMENSIONS, SPRITESHEET_DIMENSIONS, 0);
	}
	
	public static Animation getBaseAnimation() throws SlickException {
		return new Animation(getSpriteSheet(), 0, 0, 2, 0, true, animationLength, true);
	}
	
	public void die() throws SlickException {
		super.die();
		SoundManager.getInstance().play(SoundType.RobotDeath, 5f);
	}
}
