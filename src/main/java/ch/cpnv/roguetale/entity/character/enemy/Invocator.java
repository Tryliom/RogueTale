package ch.cpnv.roguetale.entity.character.enemy;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import ch.cpnv.roguetale.entity.Direction;
import ch.cpnv.roguetale.entity.character.Enemy;
import ch.cpnv.roguetale.sound.SoundManager;
import ch.cpnv.roguetale.sound.SoundType;
import ch.cpnv.roguetale.weapon.Weapon;
import ch.cpnv.roguetale.weapon.other.CreationOfLife;

public class Invocator extends Enemy {
	private static final int SPEED = 0;
	private static final int MAX_HEALTH = 20;
	private static final String SPRITESHEET_PATH = "ch\\cpnv\\roguetale\\images\\enemy\\bomber\\carac.png";
	private static final int  SPRITESHEET_DIMENSIONS = 48;

	public Invocator(Vector2f position) throws SlickException {
		super(getSpriteSheet(), 
				position, SPEED, Direction.UP, false, new CreationOfLife(), null, MAX_HEALTH);
	}
	
	public static SpriteSheet getSpriteSheet() throws SlickException {
		return new SpriteSheet(SPRITESHEET_PATH, SPRITESHEET_DIMENSIONS, SPRITESHEET_DIMENSIONS, 0);
	}
	
	public void die() throws SlickException {
		super.die();
		SoundManager.getInstance().play(SoundType.RobotDeath, 5f);
	}
	
	public void chooseAction() throws SlickException {
		if (this.getNearestEnemy() != null) {
			Weapon weapon = this.getWeapon();
			if (weapon.canAttack()) {
				weapon.attack(this);
			}
		}
	}
}
