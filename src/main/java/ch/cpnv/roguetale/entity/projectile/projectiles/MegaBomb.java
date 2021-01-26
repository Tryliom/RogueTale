package ch.cpnv.roguetale.entity.projectile.projectiles;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import ch.cpnv.roguetale.entity.Direction;
import ch.cpnv.roguetale.entity.character.Character;
import ch.cpnv.roguetale.entity.projectile.Projectile;
import ch.cpnv.roguetale.entity.temporaryeffect.areaofeffect.areas.explosions.MediumExplosion;
import ch.cpnv.roguetale.gui.guis.GameGui;

public class MegaBomb extends Projectile {
	static protected final int SPEED = 200;
	static protected final int WIDTH = 32;
	static protected final int HEIGHT = 32;
	
	static protected final String SPRITESHEET_PATH = "ch\\cpnv\\roguetale\\images\\projectiles\\bomb.png";

	public MegaBomb(Vector2f position, Direction direction, int range, int damage, Character attacker) throws SlickException {
		super(
				new SpriteSheet(SPRITESHEET_PATH, 128, 128), 
				position,
				SPEED, 
				direction,
				range,
				damage,
				attacker
				);
		this.image = this.image.getScaledCopy(WIDTH, HEIGHT);
		this.setImageDirection();
	}

	@Override
	public void setImageDirection() {}
	
	public MegaBomb(Character attacker, Direction direction, int range, int damage) throws SlickException {
		this(attacker.getPosition(), direction, range, damage, attacker);
		this.setPositionFromCharacter(attacker, direction);
	}
	
	public void onDeath() throws SlickException {
		GameGui.getAreaController().addArea(new MediumExplosion(this.getPosition(), this.damage));
		super.onDeath();
	}
}
