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
		if (this.currentHealth < 0)
			this.currentHealth = 0;
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
		currentHealth += health;
		
		if(currentHealth > maxHealth) {
			currentHealth = maxHealth;
		}
	}

}
