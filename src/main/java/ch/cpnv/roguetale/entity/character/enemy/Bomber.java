package ch.cpnv.roguetale.entity.character.enemy;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import ch.cpnv.roguetale.entity.Direction;
import ch.cpnv.roguetale.entity.character.Enemy;
import ch.cpnv.roguetale.weapon.ranged.Cannon;

public class Bomber extends Enemy {
	private static final int SPEED = 50;
	private static final int MAX_HEALTH = 2;
	private static final String SPRITESHEET_PATH = "ch\\cpnv\\roguetale\\images\\enemy\\bomber\\carac.png";
	private static final int SPRITESHEET_DIMENSIONS_X = 44;
	private static final int SPRITESHEET_DIMENSIONS_Y = 48;

	public Bomber(Vector2f position) throws SlickException {
		super(new SpriteSheet(SPRITESHEET_PATH, SPRITESHEET_DIMENSIONS_X, SPRITESHEET_DIMENSIONS_Y, 0), 
				position, SPEED, Direction.UP, false, new Cannon(), null, MAX_HEALTH);
	}
}
