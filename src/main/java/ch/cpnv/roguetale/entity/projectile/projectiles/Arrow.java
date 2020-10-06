package ch.cpnv.roguetale.entity.projectile.projectiles;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import ch.cpnv.roguetale.entity.Direction;
import ch.cpnv.roguetale.entity.character.Character;
import ch.cpnv.roguetale.entity.projectile.Projectile;

public class Arrow extends Projectile {
	static protected final int SPEED = 200;
	static protected final int IMAGE_WIDTH = 64;
	static protected final int IMAGE_HEIGHT = IMAGE_WIDTH;
	
	static protected final int HITBOX_WIDTH = IMAGE_WIDTH / 2;
	static protected final int HITBOX_HEIGHT = IMAGE_HEIGHT / 10;
	
	static protected final String SPRITESHEET_PATH = "ch\\cpnv\\roguetale\\images\\projectiles\\arrow.png";

	public Arrow(Vector2f position, Direction direction, int range, int damage) throws SlickException {
		super(
				new SpriteSheet(SPRITESHEET_PATH, 256, 256), 
				position,
				SPEED,
				direction,
				range,
				damage
				);
		this.image = this.spritesheet.getSprite(3, 0);
		this.image = this.image.getScaledCopy(IMAGE_WIDTH, IMAGE_HEIGHT);
		this.setImageDirection();
	}
	
	public Arrow(Character attacker, Direction direction, int range, int damage) throws SlickException {
		this(attacker.getPosition(), direction, range, damage);
		this.setPositionFromCharacter(attacker, direction);
	}
	
	@Override
	public String toString() {
		return "Arrow (" + position.x + ", " + position.y + ")";
	}
	
	@Override
	protected void setImageDirection() {
		switch (this.direction) {
			case UP:
				image.setRotation(270);
				break;
			case DOWN:
				image.setRotation(90);
				break;
			case LEFT:
				image.setRotation(180);
				break;
			case RIGHT:
				break;
			default:
				break;
		}
	}
	
	@Override
	public int getHitboxWidth() {
		switch(direction) {
		case UP:
		case DOWN:
			return HITBOX_HEIGHT;
		case RIGHT:
		case LEFT:
		default:
			return HITBOX_WIDTH;
		}
	}
	
	@Override
	public int getHitboxHeight() {
		switch(direction) {
		case UP:
		case DOWN:
			return HITBOX_WIDTH;
		case RIGHT:
		case LEFT:
		default:
			return HITBOX_HEIGHT;
		}
	}
}
