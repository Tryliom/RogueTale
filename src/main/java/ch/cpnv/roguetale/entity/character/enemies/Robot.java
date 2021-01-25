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
import ch.cpnv.roguetale.sound.SoundManager;
import ch.cpnv.roguetale.sound.SoundType;
import ch.cpnv.roguetale.weapon.melee.Knife;
import ch.cpnv.roguetale.weapon.ranged.Bow;

public class Robot extends Enemy {
	private static final int SPEED = 30;
	private static final int MAX_HEALTH = 300;
	private static final int MONEY_REWARD = 1;
	private static final int XP_REWARD = 5;
	private static final String SPRITESHEET_PATH = "ch\\cpnv\\roguetale\\images\\enemy\\robot\\carac.png";
	private static final int  SPRITESHEET_DIMENSIONS = 48;
	private static final int animationLength = 300;

	public Robot(Vector2f position) throws SlickException {
		super(
				"Robot", 
				getSpriteSheet(), 
				position, SPEED, Direction.UP, false, 
				null, null, 
				MAX_HEALTH, MONEY_REWARD, XP_REWARD
		);
		int alea = (int) Math.round(Math.random() * 100);
		if (alea < 50) {
			this.setPrimaryWeapon(new Bow(1000));
		} else {
			this.setPrimaryWeapon(new Knife(1000));
			this.speed = 100;
		}
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
		if (alea < 0.1) {
			GameGui.getPickupableItemController().addPickupableItem(new PickupableWeapon(new Knife(), position));
		} else if (alea < 0.2) {
			GameGui.getPickupableItemController().addPickupableItem(new PickupableWeapon(new Bow(), position));
		} else if (alea < 0.3) {
			GameGui.getPickupableItemController().addPickupableItem(new PickupableLifePoint(position));
		}
	}
	
	@Override
	public void die() throws SlickException {
		super.die();
		SoundManager.getInstance().play(SoundType.RobotDeath, 5f);
	}
}
