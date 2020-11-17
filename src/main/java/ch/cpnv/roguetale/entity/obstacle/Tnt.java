package ch.cpnv.roguetale.entity.obstacle;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import ch.cpnv.roguetale.entity.damageable.NoDamage;

public class Tnt extends Obstacle {
	protected static final int DISPLAY_DIMENSION = 32;
	static protected final String SPRITESHEET_PATH = "ch\\cpnv\\roguetale\\images\\obstacles\\icons.png";
	
	public Tnt(Vector2f position) throws SlickException {
		super(new SpriteSheet(SPRITESHEET_PATH, 48, 48), position);
		damageStrategy = new NoDamage();
		this.image = this.spritesheet.getSprite(8, 0);
		this.image = this.image.getScaledCopy(DISPLAY_DIMENSION, DISPLAY_DIMENSION);
	}
}
