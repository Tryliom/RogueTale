package ch.cpnv.roguetale.entity.projectile;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import ch.cpnv.roguetale.entity.Direction;

public class Arrow extends Projectile {
	static protected final int SPEED = 100;
	static protected final int LIFESPAN = 5000;
	static protected final int WIDTH = 32;
	static protected final int HEIGHT = 18;
	
	static protected final String SPRITESHEET_PATH = "ch\\cpnv\\roguetale\\images\\projectiles\\arrow.png";

	public Arrow(Vector2f position, Direction direction) throws SlickException {
		super(
				new SpriteSheet(SPRITESHEET_PATH, 568, 36), 
				position, 
				SPEED, 
				direction,
				LIFESPAN
				);
		this.image = this.image.getScaledCopy(WIDTH, HEIGHT);
		this.setImageDirection();
	}
	
	@Override
	public String toString() {
		return "Arrow (" + position.x + ", " + position.y + ")";
	}
}
