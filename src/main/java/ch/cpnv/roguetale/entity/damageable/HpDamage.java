package ch.cpnv.roguetale.entity.damageable;

public class HpDamage implements DamageStrategy {
	protected int maxHealth;
	protected int currentHealth;
	
	public HpDamage(int maxHp) {
		this.maxHealth = maxHp;
		this.currentHealth = maxHp;
	}

	@Override
	public void damage(int damage) {
		this.currentHealth -= damage;
	}

	@Override
	public void heal(int heal) {
		this.currentHealth += heal;
		if(currentHealth > maxHealth) {
			currentHealth = maxHealth;
		}
	}

	@Override
	public Boolean isDead() {
		return currentHealth <= 0;
	}
	
	public int getCurrentHealth() {
		return currentHealth;
	}

	public int getMaxHealth() {
		return maxHealth;
	}
	
	public void updateMaxHealth(int health) {
		maxHealth += health;
		if(health > 0) {
			currentHealth += health;
		}
		if(currentHealth > maxHealth) {
			currentHealth = maxHealth;
		}
	}

}
