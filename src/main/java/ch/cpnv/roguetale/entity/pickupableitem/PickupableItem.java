package ch.cpnv.roguetale.entity.pickupableitem;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.SpriteSheet;

import ch.cpnv.roguetale.entity.DrawableItem;
import ch.cpnv.roguetale.entity.character.Player;

public abstract class PickupableItem extends DrawableItem {
	public PickupableItem(SpriteSheet ss, Vector2f position) {
		super(ss, position);
	}
	
	public abstract void pickup(Player player);
}
