package ch.cpnv.roguetale.entity.temporaryeffect.itemeffect.effects;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import ch.cpnv.roguetale.entity.Direction;
import ch.cpnv.roguetale.entity.character.Character;
import ch.cpnv.roguetale.entity.character.Player;
import ch.cpnv.roguetale.entity.temporaryeffect.itemeffect.ItemEffect;

public class PlantedArrow extends ItemEffect {
	private static final String DEFAULT_PATH = "ch\\cpnv\\roguetale\\images\\projectiles\\arrow.png";
	private float offsetX;
	private float offsetY;

	public PlantedArrow(Vector2f position, Direction direction, Character target) throws SlickException {
		super(new SpriteSheet(DEFAULT_PATH, 128, 128, 0), position, 500);
		initImage(direction);
		this.offsetX = target.getPosition().getX() - position.getX();
		this.offsetY = target.getPosition().getY() - position.getY();
	}
		
	private void initImage(Direction direction) {
		this.image = this.spritesheet.getSprite(0, 0);
		this.image = this.image.getScaledCopy(32, 32);
		switch (direction) {
			case UP:
				this.image.rotate(-43);
				break;
			case DOWN:
				this.image.rotate(138);
				break;
			case LEFT:
				this.image.rotate(-133);
				break;
			case RIGHT:
				this.image.rotate(48);
				break;
		}
	}
	
	@Override
	public void setPosition(Vector2f position) {
		this.position = new Vector2f(position.getX() - this.offsetX, position.getY() - this.offsetY);
	}
	
	@Override
	public void draw(Vector2f origin, GameContainer gc) {
		this.image.setAlpha(this.remainingTime/500f);
		this.draw(origin, gc, null);
	}
	

}
