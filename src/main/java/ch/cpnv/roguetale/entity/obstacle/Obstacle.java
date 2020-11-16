package ch.cpnv.roguetale.entity.obstacle;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.SpriteSheet;

import ch.cpnv.roguetale.damageable.DamageStrategy;
import ch.cpnv.roguetale.damageable.Damageable;
import ch.cpnv.roguetale.entity.DrawableItem;

public abstract class Obstacle extends DrawableItem implements Damageable {
	protected DamageStrategy damageStrategy;
	
	public Obstacle(SpriteSheet ss, Vector2f position) {
		super(ss, position);
	}
	
	public void updateHealth(int health) {
		damageStrategy.updateHealth(health);
	};
	public void damage(int damage) {
		damageStrategy.damage(damage);
	};
	public void heal(int heal) {
		damageStrategy.heal(heal);
	};
	
	public Boolean isDead() {
		return damageStrategy.isDead();
	};
	
	public void onDeath() {
		// By default, do nothing
	}
}
