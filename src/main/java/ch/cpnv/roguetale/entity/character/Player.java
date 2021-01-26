package ch.cpnv.roguetale.entity.character;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import ch.cpnv.roguetale.controller.MoneyController;
import ch.cpnv.roguetale.entity.Direction;
import ch.cpnv.roguetale.entity.character.abilities.Dash;
import ch.cpnv.roguetale.entity.character.states.buff.Invincible;
import ch.cpnv.roguetale.gui.guis.GameGui;
import ch.cpnv.roguetale.main.Main;
import ch.cpnv.roguetale.purchase.Purchase;
import ch.cpnv.roguetale.purchase.purchases.BonusSpeed;
import ch.cpnv.roguetale.purchase.purchases.DashCooldownReduction;
import ch.cpnv.roguetale.purchase.purchases.HealthPlus;
import ch.cpnv.roguetale.purchase.purchases.MidasTouch;
import ch.cpnv.roguetale.weapon.Weapon;

public class Player extends Character {
	// not Integer.Max, because it creates some undesired effects when a character is dealt multiple times INFINITEDAMAGE damage
	protected final static int INFINITEDAMAGE = 100000;
	protected final static int STARTING_MAX_HEALTH = 500;
	private static final String DEAD_ANIMATION = "ch\\cpnv\\roguetale\\images\\player\\dead.png";
	private static final String SPRITESHEET_PATH = "ch\\cpnv\\roguetale\\images\\player\\carac.png";
	private static final int SPRITESHEET_DIMENSIONS = 48;
	private static final int animationLength = 300;
	
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
		super("", getSpriteSheet(), position, speed, direction, moving, primaryWeapon, secondaryWeapon, 
				(int) (Math.round(STARTING_MAX_HEALTH) * (1f + Main.saveController.getPurchase().getPurchase(HealthPlus.class).getLevel() * 0.05f)));
		level = 0;
		currentExp = 0;
		maxExp = 100;
		// Do not use addAbility, because we are not sure the UiController exists at that time
		this.abilities.add(new Dash());
		this.initDeathAnimation();
		int healthPlus = Main.saveController.getPurchase().getPurchase(HealthPlus.class).getLevel();
		this.addBonusMaxHealth(healthPlus * 0.05f);
		this.getFaction().setColor(new Color(1, 1, 1, 0));
	}

	private void initDeathAnimation() throws SlickException {
		this.deathAnimation = new Animation(new SpriteSheet(new Image(DEAD_ANIMATION), SPRITESHEET_DIMENSIONS, SPRITESHEET_DIMENSIONS), animationLength);
	}
	
	public Dash getDash() {
		for (Ability ability : this.abilities) {
			if (ability instanceof Dash) {
				Purchase purchaseDashCDReduction = Main.saveController.getPurchase().getPurchase(DashCooldownReduction.class);
				ability.setCooldown(Math.round(ability.getCooldown() * (1f - purchaseDashCDReduction.getLevel() * 0.10f)));
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
			this.levelup();
		} else {
			currentExp = totExp;
		}
	}
	
	@Override
	public void levelup() throws SlickException {
		super.levelup();
		Purchase purchaseBonusSpeed = Main.saveController.getPurchase().getPurchase(BonusSpeed.class);
		
		this.bonusSpeed += purchaseBonusSpeed.getLevel() / 100f;
		
		if (this.level % 5 == 0) {
			this.updateMaxHealth(50);
		}
		
		this.heal(this.getMaxHealth() - this.getCurrentHealth());
	}
	
	@Override
	public void damage(int damage) {
		if(!invulnerable) {
			// Let the player with 1 hp when he takes a big damage and he is full life
			if (damage > this.getMaxHealth() && this.getCurrentHealth() == this.getMaxHealth())
				damage = this.getMaxHealth() - 1;
			super.damage(damage);
			if (!this.hasState(Invincible.class))
				this.addState(new Invincible(1000));
		}
	}
	
	@Override
	public void heal(int heal) {
		Purchase midasTouch = Main.saveController.getPurchase().getPurchase(MidasTouch.class);
		if (this.getCurrentHealth() == this.getMaxHealth()) {
			MoneyController.getInstance().addMoney(10 * midasTouch.getLevel());
		}
		super.heal(heal);
	}
	
	
}
