package ch.cpnv.roguetale.entity.temporaryeffect.itemeffect.effects;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import ch.cpnv.roguetale.entity.Direction;
import ch.cpnv.roguetale.entity.temporaryeffect.itemeffect.ItemEffect;

public class ActiveShield extends ItemEffect {
	private static final String DEFAULT_PATH = "ch\\cpnv\\roguetale\\images\\effects\\shield.png";
	private static String ICON_PATH = "ch\\cpnv\\roguetale\\images\\ui\\icon\\shield.png";
	private float offsetX;
	private float offsetY;

	public ActiveShield(Vector2f position, int remainingTime, Direction direction, int offsetX, int offsetY) throws SlickException {
		super(new SpriteSheet(DEFAULT_PATH, 64, 64, 0), position, remainingTime, true);
		initImage(direction);
		this.offsetX = offsetX;
		this.offsetY = offsetY;
	}
	
	@Override
	public void setPosition(Vector2f position) {
		this.position = new Vector2f(position.getX() - this.offsetX, position.getY() - this.offsetY);
	}
	
	private void initImage(Direction direction) throws SlickException {
		this.image = this.spritesheet.getSprite(0, 0);
		switch (direction) {
			case UP:
				this.image.rotate(90);
				break;
			case DOWN:
				this.image = new SpriteSheet(ICON_PATH, 30, 30, 0).getSprite(0, 0);
				break;
			case LEFT:
				break;
			case RIGHT:
				break;
		}
	}

}
