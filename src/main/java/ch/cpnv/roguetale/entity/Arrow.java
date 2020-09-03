package main.java.ch.cpnv.roguetale.entity;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Arrow extends Projectile {
	static protected final int SPEED = 10;
	static protected final int LIFESPAN = 500;
	static protected final String SPRITESHEET_PATH = "main\\java\\ch\\cpnv\\roguetale\\images\\projectile\\IS - Arrow 568.png";
	
	public Arrow(Vector2f position, Direction direction) throws SlickException {
		super(
				new SpriteSheet(SPRITESHEET_PATH, 568, 36), 
				position, 
				SPEED, 
				direction,
				LIFESPAN
				);	
	}
}
