package ch.cpnv.roguetale.entity.temporaryeffect.areaofeffect;

import java.util.HashMap;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Shape;

import ch.cpnv.roguetale.entity.DrawableItem;
import ch.cpnv.roguetale.entity.temporaryeffect.TemporaryEffect;

public class AreaOfEffect extends TemporaryEffect {
	protected int damage;
	protected Shape area;
	protected int delay;
	protected HashMap<DrawableItem, Integer> cooldownEntites = new HashMap<>();

	public AreaOfEffect(SpriteSheet ss, Vector2f position, int lifespan, int damage, Shape area, int delay) {
		super(ss, position, lifespan);
		this.damage = damage;
		this.area = area;
		this.delay = delay;
	}
	
	public void update(int delta) throws SlickException {
		this.remainingTime -= delta;
		this.updateCooldown(delta);
		super.update(delta);
	}
	
	@Override
	public Shape getHitbox() {
		return this.area;
	}
	
	private void updateCooldown(int delta) {
		for (DrawableItem drawableItem : this.cooldownEntites.keySet()) {
			int delay = this.cooldownEntites.get(drawableItem);
			
			if (delay > 0) {
				delay -= delta;
				this.cooldownEntites.put(drawableItem, delay);
			}
		}
	}
	
	public boolean isInCooldown(DrawableItem drawableItem) {
		return this.hasInCooldown(drawableItem) && cooldownEntites.get(drawableItem) > 0;
	}
	
	public boolean hasInCooldown(DrawableItem drawableItem) {
		return cooldownEntites.containsKey(drawableItem);
	}

	public int getLifespan() {
		return remainingTime;
	}

	public void setLifespan(int lifespan) {
		this.remainingTime = lifespan;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public Shape getArea() {
		return area;
	}

	public void setArea(Shape area) {
		this.area = area;
	}

	public int getDelay() {
		return delay;
	}

	public void setDelay(int delay) {
		this.delay = delay;
	}

	public HashMap<DrawableItem, Integer> getCooldownEntites() {
		return cooldownEntites;
	}
}
