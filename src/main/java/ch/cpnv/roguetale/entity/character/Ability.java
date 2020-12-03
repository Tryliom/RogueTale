package ch.cpnv.roguetale.entity.character;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public abstract class Ability {
	private String name;
	private int cooldown;
	private int currentCooldown;
	private int duration;
	private int currentDuration;
	
	protected Image icon;
	
	protected Ability(String name, int cooldown, int duration) {
		this.name = name;
		this.cooldown = cooldown;
		this.duration = duration;
	}

	public void activate(Character user) {
		if (this.canUse()) {
			this.currentCooldown = this.cooldown;
			this.currentDuration = this.duration;
		}
	}
	
	public void onUse(Character user) throws SlickException {}
	
	public void update(int delta, Character user) throws SlickException {
		if (!canUse() && !isUsing())
			this.currentCooldown -= delta;
		if (this.isUsing()) {
			this.onUse(user);
			this.currentDuration -= delta;
		}
	}
	
	public Boolean canUse() {
		return this.currentCooldown <= 0;
	}

	public Boolean isUsing() {
		return this.currentDuration >= 0;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCooldown() {
		return cooldown;
	}

	public void setCooldown(int cooldown) {
		this.cooldown = cooldown;
	}

	public int getCurrentCooldown() {
		return currentCooldown;
	}

	public void setCurrentCooldown(int currentCooldown) {
		this.currentCooldown = currentCooldown;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int getCurrentDuration() {
		return currentDuration;
	}

	public void setCurrentDuration(int currentDuration) {
		this.currentDuration = currentDuration;
	}
	
	public Image getIcon() {
		return icon;
	}
	
	public float getCooldownPercent() {
		float cooldownPercent = (float) getCurrentCooldown() / getCooldown();
		return clampPercent(cooldownPercent);
	}
	
	public float getDurationPercent() {
		float durationPercent = (float) getCurrentDuration() / getDuration();
		return clampPercent(durationPercent);
	}
	
	public abstract Image getButtonIcon();
	
	protected float clamp(float value, float min, float max) {
		if (value < min) {
			return min;
		}
		if (value > max) {
			return max;
		}
		return value;
	}
	
	protected float clampPercent(float value) {
		return clamp(value, 0, 1);
	}
}
