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
import ch.cpnv.roguetale.entity.character.states.Invincible;
import ch.cpnv.roguetale.entity.character.states.Phantom;
import ch.cpnv.roguetale.entity.damageable.Damageable;
import ch.cpnv.roguetale.entity.damageable.HpDamage;
import ch.cpnv.roguetale.entity.obstacle.Obstacle;
import ch.cpnv.roguetale.entity.temporaryeffect.itemeffect.effects.Damage;
import ch.cpnv.roguetale.entity.temporaryeffect.itemeffect.effects.Heal;
import ch.cpnv.roguetale.gui.guis.GameGui;
import ch.cpnv.roguetale.sound.SoundManager;
import ch.cpnv.roguetale.sound.SoundType;
import ch.cpnv.roguetale.weapon.RangedWeapon;
import ch.cpnv.roguetale.weapon.Weapon;

public abstract class Character extends MovableItem implements Damageable {
	protected Weapon primaryWeapon;
	protected Weapon secondaryWeapon;
	protected Faction faction;
	protected HpDamage hpDamageStrategy;
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
		hpDamageStrategy = new HpDamage(maxHealth);
		this.primaryWeapon = primaryWeapon;
		this.secondaryWeapon = secondaryWeapon;
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
	
	@Override
	public void damage(int damage) {
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
		return hpDamageStrategy.getMaxHealth();
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
		for (State state : this.states) {
			if (state instanceof Phantom) {
				return true;
			}
		}
		
		return false;
	}
	
	public boolean hasInvincibleState() {
		for (State state : this.states) {
			if (state instanceof Invincible) {
				return true;
			}
		}
		
		return false;
	}
	
	private void removeExpiredStates() throws SlickException {
		for (Iterator<State> iterator = this.states.iterator(); iterator.hasNext();) {
			State state = iterator.next();
			if (state.isExpired()) {
				iterator.remove();
			}
		}
	}
}
