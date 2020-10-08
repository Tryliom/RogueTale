package ch.cpnv.roguetale.entity.effect;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.SpriteSheet;

import ch.cpnv.roguetale.entity.DrawableItem;
import ch.cpnv.roguetale.entity.Temporary;
import ch.cpnv.roguetale.entity.TemporaryEffect;

public class ItemEffect extends TemporaryEffect {
	protected int remainingTime;
	
	public ItemEffect(SpriteSheet ss, Vector2f position, int remainingTime) {
		super(ss, position, remainingTime);
	}
}
