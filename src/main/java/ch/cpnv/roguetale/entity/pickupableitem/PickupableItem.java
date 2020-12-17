package ch.cpnv.roguetale.entity.pickupableitem;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import ch.cpnv.roguetale.entity.DrawableItem;
import ch.cpnv.roguetale.entity.character.Player;
import ch.cpnv.roguetale.gui.guis.GameGui;

public abstract class PickupableItem extends DrawableItem {
	public PickupableItem(SpriteSheet ss, Vector2f position) {
		super(ss, position);
	}
	
	public PickupableItem(Image image, Vector2f position) {
		super(image, position);
	}
	
	public void pickup(Player player) throws SlickException {
		GameGui.getPickupableItemController().removePickupableItem(this);
	}
}
