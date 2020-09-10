package ch.cpnv.roguetale.entity.character;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.SpriteSheet;

import ch.cpnv.roguetale.entity.Direction;
import ch.cpnv.roguetale.weapon.Weapon;

public class Player extends Character {
	protected int level;
	protected int currentExp;
	protected int maxExp;

	public Player(SpriteSheet ss, Vector2f position, int speed, Direction direction, boolean moving, Weapon primaryWeapon,
			Weapon secondaryWeapon) {
		super(ss, position, speed, direction, moving, primaryWeapon, secondaryWeapon);
		level = 1;
		currentExp = 0;
		maxExp = 100;
	}

	public int getLevel() {
		return level;
	}

	public int getCurrentExp() {
		return currentExp;
	}

	public int getMaxExp() {
		return maxExp;
	}
	
	public void updateExp(int exp) {
		int totExp = this.currentExp + exp;
		
		if (totExp >= this.maxExp) {
			// Level up !
			this.currentExp = totExp - this.maxExp;
			this.maxExp += 50;
			this.level++;
			this.maxHealth++;
			this.updateHealth(1);
			
		} else {
			this.currentExp = totExp;
		}
	}

}
