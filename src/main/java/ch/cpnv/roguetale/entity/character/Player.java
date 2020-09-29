package ch.cpnv.roguetale.entity.character;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.SpriteSheet;
import ch.cpnv.roguetale.entity.Direction;
import ch.cpnv.roguetale.weapon.Weapon;

public class Player extends Character {
	protected final static int STARTING_MAX_HEALTH = 3;
	
	protected int level;
	protected int currentExp;
	protected int maxExp;
	
	protected boolean invulnerable = false;
	protected boolean oneHitKill = false;

	public Player(SpriteSheet ss, 
			Vector2f position, 
			int speed, 
			Direction direction, 
			boolean moving, 
			Weapon primaryWeapon,
			Weapon secondaryWeapon
			) {
		super(ss, position, speed, direction, moving, primaryWeapon, secondaryWeapon, STARTING_MAX_HEALTH);
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
	
	public void setInvulnerable(boolean invulnerable) {
		this.invulnerable = invulnerable;
	}
	
	public void setOneHitKill() {
		this.oneHitKill = true;
		
		if(primaryWeapon != null) { 
			primaryWeapon.setDamage(Integer.MAX_VALUE);
			//System.out.println("max primary damage");
		}
		
		if(secondaryWeapon != null) { 
			secondaryWeapon.setDamage(Integer.MAX_VALUE);
			//System.out.println("max secondary damage");
		}
	}
	
	@Override
	public void setPrimaryWeapon(Weapon weapon) {
		super.setPrimaryWeapon(weapon);
		if(oneHitKill) {
			setOneHitKill();
		}
	}
	
	@Override
	public void setSecondaryWeapon(Weapon weapon) {
		super.setSecondaryWeapon(weapon);
		if(oneHitKill) {
			setOneHitKill();
		}
	}
	
	public void updateExp(int exp) {
		int totExp = currentExp + exp;
		
		if (totExp >= this.maxExp) {
			// Level up !
			currentExp = totExp - maxExp;
			maxExp += 50;
			level++;
			maxHealth++;
			updateHealth(1);
			
		} else {
			currentExp = totExp;
		}
	}
	
	@Override
	public void updateHealth(int health) {
		if(!invulnerable || health >= 0) {
			super.updateHealth(health);
		}
	}
}
