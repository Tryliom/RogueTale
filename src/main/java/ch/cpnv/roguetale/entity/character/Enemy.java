package ch.cpnv.roguetale.entity.character;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.SpriteSheet;

import ch.cpnv.roguetale.entity.Direction;
import ch.cpnv.roguetale.weapon.Weapon;

public class Enemy extends Character {

	public Enemy(SpriteSheet ss, Vector2f position, int speed, Direction direction, boolean moving,
			Weapon primaryWeapon, Weapon secondaryWeapon) {
		super(ss, position, speed, direction, moving, primaryWeapon, secondaryWeapon);
	}
	
	public void act() {
		
	}

	public void moveTowardPlayer(Player p) {
		float enX = this.getPosition().getX();
		float enY = this.getPosition().getY();
	}

}
