package ch.cpnv.roguetale.entity.areaofeffect.areas;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Shape;

import ch.cpnv.roguetale.entity.temporaryeffect.areaofeffect.AreaOfEffect;

public class Explosion extends AreaOfEffect {
	private final static int LIFE = 500;
	private final static int DELAY = 1000;

	public Explosion(SpriteSheet spritesheet, Vector2f position, int damage, Shape area) throws SlickException {
		super(spritesheet, position, LIFE, damage, area, DELAY);
	}

}
