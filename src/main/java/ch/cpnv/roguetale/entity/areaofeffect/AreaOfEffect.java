package ch.cpnv.roguetale.entity.areaofeffect;

import java.util.HashMap;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Shape;

import ch.cpnv.roguetale.entity.DrawableItem;
import ch.cpnv.roguetale.entity.character.Character;

public class AreaOfEffect extends DrawableItem {
	protected int lifespan;
	protected int damage;
	protected Shape area;
	protected int delay;
	protected HashMap<Character, Integer> cooldownEntites = new HashMap<Character, Integer>();

	public AreaOfEffect(SpriteSheet ss, Vector2f position, int lifespan, int damage, Shape area, int delay) {
		super(ss, position);
		this.lifespan = lifespan;
		this.damage = damage;
		this.area = area;
		this.delay = delay;
	}
	
	public void update(int delta) throws SlickException {
		this.lifespan -= delta;
		this.updateCooldown(delta);
	}
	
	@Override
	public Boolean isColliding(DrawableItem collisionCandidate) {
		return this.area.intersects(collisionCandidate.getHitbox());
	}
	
	private void updateCooldown(int delta) {
		for (Character en : this.cooldownEntites.keySet()) {
			int delay = this.cooldownEntites.get(en);
			
			if (delay > 0) {
				delay--;
				this.cooldownEntites.put(en, delay);
			}
		}
	}
	
	public boolean isEntityInCooldown(Character entity) {
		return this.hasEntityCooldown(entity) && cooldownEntites.get(entity) > 0;
	}
	
	public boolean hasEntityCooldown(Character entity) {
		return cooldownEntites.containsKey(entity);
	}
	
	public boolean isExpired() {
		return this.lifespan <= 0;
	}

	public int getLifespan() {
		return lifespan;
	}

	public void setLifespan(int lifespan) {
		this.lifespan = lifespan;
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

	public HashMap<Character, Integer> getCooldownEntites() {
		return cooldownEntites;
	}

}
