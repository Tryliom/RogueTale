package ch.cpnv.roguetale.entity.character.enemies;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import ch.cpnv.roguetale.entity.Direction;
import ch.cpnv.roguetale.entity.character.Enemy;
import ch.cpnv.roguetale.entity.pickupableitem.PickupableLifePoint;
import ch.cpnv.roguetale.entity.pickupableitem.PickupableWeapon;
import ch.cpnv.roguetale.gui.guis.GameGui;
import ch.cpnv.roguetale.weapon.ranged.Cannon;

public class Bomber extends Enemy {
	private static final int SPEED = 50;
	private static final int MAX_HEALTH = 2;
	private static final int MONEY_REWARD = 2;
	private static final int XP_REWARD = 15;
	private static final String SPRITESHEET_PATH = "ch\\cpnv\\roguetale\\images\\enemy\\bomber\\carac.png";
	private static final int SPRITESHEET_DIMENSIONS = 48;
	private static final int animationLength = 300;

	public Bomber(Vector2f position) throws SlickException {
		super(
				"Bomber", 
				getSpriteSheet(), 
				position, SPEED, Direction.UP, false, 
				new Cannon(), null, 
				MAX_HEALTH, MONEY_REWARD, XP_REWARD
		);
	}
	
	public static SpriteSheet getSpriteSheet() throws SlickException {
		return new SpriteSheet(SPRITESHEET_PATH, SPRITESHEET_DIMENSIONS, SPRITESHEET_DIMENSIONS, 0);
	}
	
	public static Animation getBaseAnimation() throws SlickException {
		return new Animation(getSpriteSheet(), 0, 0, 2, 0, true, animationLength, true);
	}
	
	@Override
	protected void dropOnDeath() throws SlickException {
		double alea = Math.random();
		if (alea < 0.3) {
			GameGui.getPickupableItemController().addPickupableItem(new PickupableWeapon(new Cannon(), position));
		} else {
			GameGui.getPickupableItemController().addPickupableItem(new PickupableLifePoint(position));
		}
	}
	
	@Override
	protected void die() throws SlickException {		
		super.die();
	}
}
