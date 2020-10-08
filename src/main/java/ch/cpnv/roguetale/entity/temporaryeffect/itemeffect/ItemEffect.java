package ch.cpnv.roguetale.entity.temporaryeffect.itemeffect;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.SpriteSheet;

import ch.cpnv.roguetale.entity.temporaryeffect.TemporaryEffect;

public class ItemEffect extends TemporaryEffect {
	protected int remainingTime;
	
	public ItemEffect(SpriteSheet ss, Vector2f position, int remainingTime) {
		super(ss, position, remainingTime);
	}
}
