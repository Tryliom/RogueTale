package ch.cpnv.roguetale.entity.areaofeffect.areas.explosions;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Circle;

import ch.cpnv.roguetale.entity.areaofeffect.areas.Explosion;

public class MinorExplosion extends Explosion {
	private final static String SPRITESHEET_PATH = "ch\\cpnv\\roguetale\\images\\areas\\explosion.png";
	
	public MinorExplosion(Vector2f position, int damage) throws SlickException {
		super(new SpriteSheet(SPRITESHEET_PATH, 82, 91), position, damage, new Circle(position.getX(), position.getY(), 45));
	}

}
