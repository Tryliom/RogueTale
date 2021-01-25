package ch.cpnv.roguetale.entity.character;

import java.util.ArrayList;
import java.util.Iterator;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;

import ch.cpnv.roguetale.entity.Direction;
import ch.cpnv.roguetale.entity.MovableItem;
import ch.cpnv.roguetale.entity.character.states.buff.Invincible;
import ch.cpnv.roguetale.entity.character.states.buff.Phantom;
import ch.cpnv.roguetale.entity.character.states.buff.Speed;
import ch.cpnv.roguetale.entity.character.states.debuff.Slowness;
import ch.cpnv.roguetale.entity.damageable.Damageable;
import ch.cpnv.roguetale.entity.damageable.HpDamage;
import ch.cpnv.roguetale.entity.obstacle.Obstacle;
import ch.cpnv.roguetale.entity.temporaryeffect.itemeffect.effects.Damage;
import ch.cpnv.roguetale.entity.temporaryeffect.itemeffect.effects.Heal;
import ch.cpnv.roguetale.font.FontManager;
import ch.cpnv.roguetale.font.FontType;
import ch.cpnv.roguetale.gui.guis.GameGui;
import ch.cpnv.roguetale.main.Main;
import ch.cpnv.roguetale.purchase.purchases.HUDEnemyLevel;
import ch.cpnv.roguetale.purchase.purchases.HUDEnemyLife;
import ch.cpnv.roguetale.sound.SoundManager;
import ch.cpnv.roguetale.sound.SoundType;
import ch.cpnv.roguetale.weapon.RangedWeapon;
import ch.cpnv.roguetale.weapon.Weapon;

public abstract class Character extends MovableItem implements Damageable {
	protected static final int FACTION_ICON_OFFSET = 5;
	protected static final int FACTION_ICON_DIMENSION = 10;
	
	protected int level;
	
	protected String name;
	protected Weapon primaryWeapon;
	protected Weapon secondaryWeapon;
	protected Faction faction;
	protected HpDamage hpDamageStrategy;
	protected ArrayList<State> states = new ArrayList<State>();
	protected ArrayList<Ability> abilities = new ArrayList<Ability>();
	protected float bonusSpeed;
	protected float bonusMaxHealth;

	public Character(
			String name,
			SpriteSheet ss, 
			Vector2f position, 
			int speed, 
			Direction direction, 
			boolean moving, 
			Weapon primaryWeapon, 
			Weapon secondaryWeapon,
			int maxHealth
		) {
		super(ss, position, speed, direction, moving);
		hpDamageStrategy = new HpDamage(maxHealth);
		this.primaryWeapon = primaryWeapon;
		this.secondaryWeapon = secondaryWeapon;
		this.faction = new Faction();
		this.name = name;
		this.bonusSpeed = 0;
	}
	
	public void update(int delta) throws SlickException {
		super.update(delta);
		for (State state : this.states)
			state.update(delta);
		for (Ability ability : this.abilities)
			ability.update(delta, this);
		this.removeExpiredStates();
	}
	
	public void move(int delta) throws SlickException {
		int oldSpeed = this.speed;
		// Apply slowness
		if (this.hasState(Slowness.class)) {
			Slowness slow = (Slowness) this.getState(Slowness.class);
			this.speed *= slow.getSlowness()/100f;
		}
		int coeff = 1;
		// Apply coefficient of speed
		if (this.hasState(Speed.class)) {
			Speed speed = (Speed) this.getState(Speed.class);
			coeff = speed.getSpeed();
		}
		
		// Apply bonus speed
		coeff *= 1 + this.bonusSpeed;
		
		// Move
		super.move(delta * coeff);
		Character collidingEntity = getCollidingCharacter();
		Obstacle collidingObstacle = getCollidingObstacle();

		// Undo the move if there is a collision
		if (collidingEntity != null && !this.hasState(Phantom.class) && !collidingEntity.hasState(Phantom.class)) {
			super.move(delta * -1 * coeff);
		} else if (collidingObstacle != null) {
			super.move(delta * -1 * coeff);
		}
		
		this.speed = oldSpeed;
	}
	
	public void draw(Vector2f origin, GameContainer gc, Color filter) {
		if (this.isDead() && this.deathAnimation != null) {
			float xPositionDrawing = this.position.x - origin.x - this.image.getWidth() / 2;
			float yPositionDrawing = - (this.position.y - origin.y + this.image.getHeight() / 2);
			if (filter != null) {
				this.deathAnimation.draw(xPositionDrawing, yPositionDrawing, filter);
			}
			else {
				this.deathAnimation.draw(xPositionDrawing, yPositionDrawing);
			}
		} else {
			Graphics g = gc.getGraphics();
			Color old = g.getColor();
			
			g.setColor(this.faction.getColor());
			g.fill(new Rectangle(
					this.position.x - origin.x - FACTION_ICON_OFFSET, 
					- (this.position.y - origin.y - this.image.getHeight()/2) + FACTION_ICON_OFFSET, 
					FACTION_ICON_DIMENSION, 
					FACTION_ICON_DIMENSION)
			); 
			
			if (!(this instanceof Player)) {
				FontManager.getInstance().setFont(FontType.Small, g);
				boolean displayLevel = Main.saveController.getPurchase().getPurchase(HUDEnemyLevel.class).getLevel() == 1;
				boolean displayLife = Main.saveController.getPurchase().getPurchase(HUDEnemyLife.class).getLevel() == 1;
				String str = "";
				if (displayLevel)
					str += "Niveau "+this.level;
				if (displayLife) {
					if (!str.isEmpty())
						str += " | ";
					str += "Vie "+this.getCurrentHealth() + " <3";
				}
				g.drawString(str, this.position.x - origin.x + 20, - (this.position.y - origin.y - this.image.getHeight()/2));
				FontManager.getInstance().resetDefaultFont(g);
			}
			g.setColor(old);
			
			super.draw(origin, gc, filter);
		}
	}
	
	@Override
	public void damage(int damage) {
		if (this.hasState(Invincible.class))
			return;
		try {
			this.activeEffects.add(new Damage(this.getPosition()));
			SoundManager.getInstance().play(SoundType.Hurt);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		hpDamageStrategy.damage(damage);
	};
	@Override
	public void heal(int heal) {
		hpDamageStrategy.heal(heal);
		try {
			this.activeEffects.add(new Heal(this.getPosition()));
		} catch (SlickException e) {
			e.printStackTrace();
		}
	};
	@Override
	public Boolean isDead() {
		return hpDamageStrategy.isDead();
	};
	
	public int getCurrentHealth() {
		return hpDamageStrategy.getCurrentHealth();
	}
	public int getMaxHealth() {
		return Math.round(hpDamageStrategy.getMaxHealth() * (1 + this.getBonusMaxHealth()));
	}
	
	public ArrayList<Ability> getAbilities() {
		return abilities;
	}
	
	public void updateMaxHealth(int health) throws SlickException {
		hpDamageStrategy.updateMaxHealth(health);
	}

	public void setPrimaryWeapon(Weapon weapon) {
		this.primaryWeapon = weapon;
	}

	public void setSecondaryWeapon(Weapon weapon) {
		this.secondaryWeapon = weapon;
	}
	
	public Weapon getPrimaryWeapon() {
		return primaryWeapon;
	}

	public Weapon getSecondaryWeapon() {
		return secondaryWeapon;
	}
	
	public void aimWeapon(Weapon weapon, int delta) throws SlickException {
		if (weapon != null) {
			weapon.aim(delta, this);
		}
	}
	
	public void attackWithWeapon(Weapon weapon) throws SlickException {
		if (weapon != null) {
			weapon.attack(this);
		}
	}
	
	public void reduceCooldown(int delta) {
		if (primaryWeapon != null)
			primaryWeapon.reduceCooldown(delta);
		if (secondaryWeapon != null)
			secondaryWeapon.reduceCooldown(delta);
	}
	
	public boolean isAiming() {
		Weapon first = this.getPrimaryWeapon();
		Weapon second = this.getSecondaryWeapon();
		
		return first instanceof RangedWeapon && ((RangedWeapon) first).isAiming() 
				|| second instanceof RangedWeapon && ((RangedWeapon) second).isAiming();
	}

	public Character getNearestOpponent() {
		ArrayList<Character> list = this.getCharacterList();
		
		Character nearest = null;
		for (Character entity : list) {
			
			if (entity.getFaction().getId() != this.getFaction().getId()) {
				if ((nearest == null || this.getDistanceToMovableItem(entity) < this.getDistanceToMovableItem(nearest))) {
					nearest = entity;
				}
			}
		}
		
		return nearest;
	}
	
	public ArrayList<Character> getCharacterList() {
		ArrayList<Character> list = new ArrayList<Character>();
		list.addAll(GameGui.getEnemyController().getEnemies());
		list.add(GameGui.getPlayerController().getPlayer());
		
		return list;
	}
	
	public Faction getFaction() {
		return faction;
	}

	public void setFaction(Faction faction) {
		this.faction = faction;
	}
	
	public void addState(State state) {
		this.states.add(state);
	}
	
	public void addAbility(Ability ability) {
		this.abilities.add(ability);
	}
	
	/*
	 * Check if character is in a certain state
	 * Class<?> cls		State class
	 */
	public boolean hasState(Class<?> cls) {
		for (State state : this.states) {
			if (cls.isInstance(state)) {
				return true;
			}
		}
		
		return false;
	}
	
	/*
	 * Return a state based on his class
	 * Class<?> cls		State class
	 */
	public State getState(Class<?> cls) {
		for (State state : this.states) {
			if (cls.isInstance(state)) {
				return state;
			}
		}
		
		return null;
	}
	
	private void removeExpiredStates() throws SlickException {
		for (Iterator<State> iterator = this.states.iterator(); iterator.hasNext();) {
			State state = iterator.next();
			if (state.isExpired()) {
				// Add 100ms to effect if the user is inside an entity
				if (state instanceof Phantom && this.getCollidingCharacter() != null) {
					state.updateRemainingTime(100);
				} else
					iterator.remove();
			}
		}
	}
	
	
	public void levelup() throws SlickException {
		this.level++;		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public float getBonusMaxHealth() {
		return bonusMaxHealth;
	}

	public void addBonusMaxHealth(float bonusMaxHealth) {
		this.bonusMaxHealth += bonusMaxHealth;
	}
	
}
