package ch.cpnv.roguetale.entity.character.enemy;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import ch.cpnv.roguetale.entity.Direction;
import ch.cpnv.roguetale.entity.character.Enemy;
import ch.cpnv.roguetale.weapon.Weapon;

public class Robot extends Enemy {

	public Robot(SpriteSheet ss, Vector2f position, int speed, Direction direction, boolean moving, Weapon primaryWeapon, Weapon secondaryWeapon) throws SlickException {
		super(new SpriteSheet("ch\\cpnv\\roguetale\\images\\enemy\\carac.png", 48, 48, 0), position, speed, direction, moving, primaryWeapon, secondaryWeapon);
	}

}
