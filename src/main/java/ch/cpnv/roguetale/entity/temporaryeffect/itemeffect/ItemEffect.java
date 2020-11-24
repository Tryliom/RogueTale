package ch.cpnv.roguetale.entity.temporaryeffect.itemeffect;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.SpriteSheet;

import ch.cpnv.roguetale.entity.temporaryeffect.TemporaryEffect;

public class ItemEffect extends TemporaryEffect {	
	protected Boolean foreground;
	
	public ItemEffect(SpriteSheet ss, Vector2f position, int remainingTime, boolean foreground) {
		super(ss, position, remainingTime);
		this.foreground = foreground;
	}

	public Boolean isForeground() {
		return foreground;
	}

	public void setForeground(Boolean foreground) {
		this.foreground = foreground;
	}
	
	
}
