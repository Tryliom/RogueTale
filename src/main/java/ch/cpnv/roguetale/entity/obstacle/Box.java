package ch.cpnv.roguetale.entity.obstacle;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import ch.cpnv.roguetale.entity.damageable.OneHitDamage;
import ch.cpnv.roguetale.entity.pickupableitem.PickupableWeapon;
import ch.cpnv.roguetale.gui.guis.GameGui;
import ch.cpnv.roguetale.weapon.Weapon;
import ch.cpnv.roguetale.weapon.melee.Knife;

public class Box extends Obstacle {
	protected static final int DISPLAY_DIMENSION = 32;
	static protected final String SPRITESHEET_PATH = "ch\\cpnv\\roguetale\\images\\obstacles\\icons.png";

	public Box(Vector2f position) throws SlickException {
		super(new SpriteSheet(SPRITESHEET_PATH, 48, 48), position);
		damageStrategy = new OneHitDamage();
		image = spritesheet.getSprite(4, 3);
		image = image.getScaledCopy(DISPLAY_DIMENSION, DISPLAY_DIMENSION);
	}

	@Override
	public void onDeath() {
		Weapon weapon;
		try {
			weapon = new Knife();
			GameGui.getPickupableItemController().addPickupableItem(new PickupableWeapon(weapon, getPosition()));
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
}
