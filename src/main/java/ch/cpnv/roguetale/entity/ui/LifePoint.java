package ch.cpnv.roguetale.entity.ui;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import ch.cpnv.roguetale.entity.DrawableItem;

public class LifePoint extends DrawableItem {
	protected Boolean full;
	
	static protected final String SPRITESHEET_PATH = "ch\\cpnv\\roguetale\\images\\ui\\life.png";
	
	public LifePoint() throws SlickException {
		super(new SpriteSheet(SPRITESHEET_PATH, 300, 300), new Vector2f());
		Full(true);
	}
	
	public Boolean Full()  {
		return full;
	}
	
	public void Full(Boolean full) {
		this.full = full;
		if (full) {
			this.image = this.spritesheet.getSprite(0, 0);
		} else {
			this.image = this.spritesheet.getSprite(0, 3);
		}
		
	}

}
