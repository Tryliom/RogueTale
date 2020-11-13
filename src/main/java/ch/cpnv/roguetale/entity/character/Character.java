package ch.cpnv.roguetale.entity.character;

import java.util.ArrayList;
import java.util.Iterator;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import ch.cpnv.roguetale.entity.Direction;
import ch.cpnv.roguetale.entity.MovableItem;
import ch.cpnv.roguetale.entity.character.states.Phantom;
import ch.cpnv.roguetale.entity.obstacle.Obstacle;
import ch.cpnv.roguetale.entity.temporaryeffect.itemeffect.effects.Damage;
import ch.cpnv.roguetale.entity.temporaryeffect.itemeffect.effects.Heal;
import ch.cpnv.roguetale.gui.guis.GameGui;
import ch.cpnv.roguetale.sound.SoundManager;
import ch.cpnv.roguetale.sound.SoundType;
import ch.cpnv.roguetale.weapon.RangedWeapon;
import ch.cpnv.roguetale.weapon.Weapon;

public abstract class Character extends MovableItem {
	protected int currentHealth;
	protected int maxHealth;
	protected Weapon primaryWeapon;
	protected Weapon secondaryWeapon;
	protected Faction faction;
	protected ArrayList<State> states = new ArrayList<State>();
	protected ArrayList<Ability> abilities = new ArrayList<Ability>();

	public Character(SpriteSheet ss, 
			Vector2f position, 
			int speed, 
			Direction direction, 
			boolean moving, 
			Weapon primaryWeapon, 
			Weapon secondaryWeapon,
			int maxHealth
			) {
		super(ss, position, speed, direction, moving);
		this.primaryWeapon = primaryWeapon;
		this.secondaryWeapon = secondaryWeapon;
		this.maxHealth = maxHealth;
		this.currentHealth = maxHealth;
		this.faction = new Faction();
	}
	
	public void update(int delta) throws SlickException {
		super.update(delta);
		for (State state : this.states)
			state.update(delta);
		for (Ability ability : this.abilities)
			ability.update(delta, this);
		this.removeExpiredStates();
	}
	
	public void move(int delta, boolean canPush) throws SlickException {
		int oldSpeed = this.speed;
		if (isAiming()) {
			this.speed /= 2;
		}
		super.move(delta, false);
		Character collidingEntity = getCollidingCharacter();
		Obstacle collidingObstacle = getCollidingObstacle();
		// undo the move if there is a collision
		if (collidingEntity != null) {
			Direction old = collidingEntity.getDirection();
			collidingEntity.setDirection(this.getDirection());
			// We don't want to create an infinite loop, 
			// so we really don't want to reuse this.move
			super.move(delta * -1, false);
			if (canPush)
				collidingEntity.move(delta, false);
			collidingEntity.setDirection(old);
		} else if(collidingObstacle != null) {
			super.move(delta * -1, false);
		}
		
		this.speed = oldSpeed;
	}
	
	public void draw(Vector2f origin, GameContainer gc, Color filter) {
		if (this.isDead() && this.deathAnimation != null) {
			this.deathAnimation.draw(this.position.x - origin.x - this.image.getWidth() / 2, 
					 - (this.position.y - origin.y + this.image.getHeight() / 2),
					 filter);
		} else
			super.draw(origin, gc, filter);
	}
	
	public int getCurrentHealth() {
		return currentHealth;
	}

	public int getMaxHealth() {
		return maxHealth;
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

	// TODO prevent currentHealth to become higher than maxHealth
	public void updateHealth(int health) throws SlickException {
		if (health > 0) {
			this.activeEffects.add(new Heal(this.getPosition()));
		} else if (health < 0) {
			SoundManager.getInstance().play(SoundType.Hurt);
			this.activeEffects.add(new Damage(this.getPosition()));
		}
		this.currentHealth += health;
	}
	
	public void updateMaxHealth(int health) throws SlickException {
		maxHealth += health;
		updateHealth(health);
	}
	
	public Boolean isDead() {
		return this.currentHealth <= 0;
	}
	
	public void aimWeapon(Weapon weapon, int delta) {
		if (weapon != null && weapon instanceof RangedWeapon) {
			((RangedWeapon) weapon).aim(delta);
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
		int MAX_RANGE = 1000;
		
		Character nearest = null;
		for (Character entity : list) {
			
			if (entity.getFaction().getId() != this.getFaction().getId()) {
				if ((nearest == null || this.getDistanceToMovableItem(entity) < this.getDistanceToMovableItem(nearest)) && this.getDistanceToMovableItem(entity) < MAX_RANGE) {
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
	
	public boolean hasPhantomState() {
		for (State stat : this.states) {
			if (stat instanceof Phantom) {
				return true;
			}
		}
		
		return false;
	}
	
	private void removeExpiredStates() throws SlickException {
		// The remove method does not work in a "for(Projectile projectile : projectiles)" loop
		// https://stackoverflow.com/questions/3184883/concurrentmodificationexception-for-arraylist
		for(Iterator<State> iterator = this.states.iterator(); iterator.hasNext();) {
			State state = iterator.next();
			if (state.isExpired()) {
				iterator.remove();
			}
		}
	}
}
