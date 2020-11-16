package ch.cpnv.roguetale.entity.pickupableitem;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import ch.cpnv.roguetale.entity.character.Player;

public class PickupableLifePoint extends PickupableItem {
	protected static final int DISPLAY_DIMENSION = 24;
	static protected final String SPRITESHEET_PATH = "ch\\cpnv\\roguetale\\images\\ui\\life.png";

	public PickupableLifePoint(Vector2f position) throws SlickException {
		super(new SpriteSheet(SPRITESHEET_PATH, 300, 300), position);
		this.image = this.image.getScaledCopy(DISPLAY_DIMENSION, DISPLAY_DIMENSION);
	}

	@Override
	public void pickup(Player player) throws SlickException {		
		super.pickup(player);
		
		player.heal(1);
	}
}
