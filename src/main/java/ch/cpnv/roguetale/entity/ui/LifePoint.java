package ch.cpnv.roguetale.entity.ui;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import ch.cpnv.roguetale.entity.DrawableItem;

public class LifePoint extends DrawableItem {
	protected static final int DISPLAY_DIMENSION = 30;
	static protected final String SPRITESHEET_PATH = "ch\\cpnv\\roguetale\\images\\ui\\life.png";
	
	protected Boolean full;
	
	public LifePoint() throws SlickException {
		super(new SpriteSheet(SPRITESHEET_PATH, 300, 300), new Vector2f());
		setFull(true);
	}
	
	public Boolean isFull()  {
		return full;
	}
	
	public void setFull(Boolean full) {
		this.full = full;
		if (full) {
			this.image = this.spritesheet.getSprite(0, 0);
		} else {
			this.image = this.spritesheet.getSprite(2, 0);
		}
		this.image = this.image.getScaledCopy(DISPLAY_DIMENSION, DISPLAY_DIMENSION);
		
	}

}
