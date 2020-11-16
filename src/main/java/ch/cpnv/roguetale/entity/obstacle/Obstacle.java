package ch.cpnv.roguetale.entity.obstacle;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.SpriteSheet;

import ch.cpnv.roguetale.entity.DrawableItem;
import ch.cpnv.roguetale.entity.damageable.DamageStrategy;
import ch.cpnv.roguetale.entity.damageable.Damageable;

public abstract class Obstacle extends DrawableItem implements Damageable {
	protected DamageStrategy damageStrategy;
	
	public Obstacle(SpriteSheet ss, Vector2f position) {
		super(ss, position);
	}
	
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
