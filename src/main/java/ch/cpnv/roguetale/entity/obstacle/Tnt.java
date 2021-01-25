package ch.cpnv.roguetale.entity.obstacle;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import ch.cpnv.roguetale.entity.damageable.OneHitDamage;
import ch.cpnv.roguetale.entity.temporaryeffect.areaofeffect.areas.explosions.MinorExplosion;
import ch.cpnv.roguetale.gui.guis.GameGui;

public class Tnt extends Obstacle {
	protected static final int DISPLAY_DIMENSION = 32;
	static protected final String SPRITESHEET_PATH = "ch\\cpnv\\roguetale\\images\\obstacles\\icons.png";
	
	public Tnt(Vector2f position) throws SlickException {
		super(new SpriteSheet(SPRITESHEET_PATH, 48, 48), position);
		damageStrategy = new OneHitDamage();
		image = spritesheet.getSprite(8, 0);
		image = image.getScaledCopy(DISPLAY_DIMENSION, DISPLAY_DIMENSION);
	}
	
	@Override
	public void onDeath() {
		try {
			GameGui.getAreaController().addArea(new MinorExplosion(this.getPosition(), 200));
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
}
