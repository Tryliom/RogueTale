package ch.cpnv.roguetale.entity.projectile.projectiles;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import ch.cpnv.roguetale.entity.Direction;
import ch.cpnv.roguetale.entity.character.Character;
import ch.cpnv.roguetale.entity.projectile.Projectile;

public class Bomb extends Projectile {
	static protected final int SPEED = 200;
	static protected final int WIDTH = 32;
	static protected final int HEIGHT = 18;
	
	static protected final String SPRITESHEET_PATH = "ch\\cpnv\\roguetale\\images\\projectiles\\arrow.png";

	public Bomb(Vector2f position, Direction direction, int range, int damage) throws SlickException {
		super(
				new SpriteSheet(SPRITESHEET_PATH, 568, 36), 
				position,
				SPEED, 
				direction,
				range,
				damage
				);
		this.image = this.image.getScaledCopy(WIDTH, HEIGHT);
		this.setImageDirection();
	}

	public Bomb(Character attacker, Direction direction, int range, int damage) throws SlickException {
		this(attacker.getPosition(), direction, range, damage);
		this.setPositionFromCharacter(attacker, direction);
	}
	
}
