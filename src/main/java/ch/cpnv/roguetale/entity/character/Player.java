package ch.cpnv.roguetale.entity.character;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import ch.cpnv.roguetale.entity.Direction;
import ch.cpnv.roguetale.entity.character.abilities.Dash;
import ch.cpnv.roguetale.gui.guis.GameGui;
import ch.cpnv.roguetale.weapon.Weapon;

public class Player extends Character {
	// not Integer.Max, because it creates some undesired effects when a character is dealt multiple times INFINITEDAMAGE damage
	protected final static int INFINITEDAMAGE = 10000;
	protected final static int STARTING_MAX_HEALTH = 5;
	private static final String DEAD_ANIMATION = "ch\\cpnv\\roguetale\\images\\player\\dead.png";
	private static final String SPRITESHEET_PATH = "ch\\cpnv\\roguetale\\images\\player\\carac.png";
	private static final int SPRITESHEET_DIMENSIONS = 48;
	private static final int animationLength = 300;
	
	protected int level;
	protected int currentExp;
	protected int maxExp;
	
	protected boolean invulnerable = false;
	protected boolean oneHitKill = false;

	public Player(
			Vector2f position, 
			int speed, 
			Direction direction, 
			boolean moving, 
			Weapon primaryWeapon,
			Weapon secondaryWeapon
			) throws SlickException {
		super("", getSpriteSheet(), position, speed, direction, moving, primaryWeapon, secondaryWeapon, STARTING_MAX_HEALTH);
		level = 1;
		currentExp = 0;
		maxExp = 100;
		// Do not use addAbility, because we are not sure the UiController exists at that time
		this.abilities.add(new Dash());
		this.initDeathAnimation();
	}

	private void initDeathAnimation() throws SlickException {
		this.deathAnimation = new Animation(new SpriteSheet(new Image(DEAD_ANIMATION), SPRITESHEET_DIMENSIONS, SPRITESHEET_DIMENSIONS), animationLength);
	}
	
	public Dash getDash() {
		for (Ability ability : this.abilities) {
			if (ability instanceof Dash) {
				return (Dash) ability;
			}
		}
		return null;
	}
	
	public static SpriteSheet getSpriteSheet() throws SlickException {
		return new SpriteSheet(SPRITESHEET_PATH, SPRITESHEET_DIMENSIONS, SPRITESHEET_DIMENSIONS, 0);
	}

	public static Animation getBaseAnimation() throws SlickException {
		return new Animation(getSpriteSheet(), 0, 0, 2, 0, true, animationLength, true);
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
	
	public void addAbility(Ability ability) {
		this.abilities.add(ability);
		GameGui.getUiController().addAbility(ability);
	}
	
	public void removeAbility(Ability ability) {
		this.abilities.remove(ability);
		GameGui.getUiController().removeAbility(ability);
	}
	
	public void setInvulnerable(boolean invulnerable) {
		this.invulnerable = invulnerable;
	}
	
	public void setOneHitKill() {
		this.oneHitKill = true;
		
		if(primaryWeapon != null) { 
			primaryWeapon.setDamage(INFINITEDAMAGE);
		}
		
		if(secondaryWeapon != null) { 
			secondaryWeapon.setDamage(INFINITEDAMAGE);
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
	
	public void updateExp(int exp) throws SlickException {
		int totExp = currentExp + exp;
		
		if (totExp >= this.maxExp) {
			// Level up !
			currentExp = totExp - maxExp;
			maxExp += 50;
			level++;
			updateMaxHealth(1);			
		} else {
			currentExp = totExp;
		}
	}
	
	@Override
	public void damage(int damage) {
		if(!invulnerable) {
			super.damage(damage);
		}
	}
	
	
}
