package ch.cpnv.roguetale.entity.damageable;

public class HpDamage implements DamageStrategy {
	protected int maxHealth;
	protected int currentHealth;
	protected float bonusHealth;
	
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
		return this.currentHealth;
	}

	public int getMaxHealth() {
		return this.maxHealth;
	}
	
	public void updateMaxHealth(int health) {
		int healthPlus = Math.round(health * (1.0f + this.getBonusHealth()));
		maxHealth += healthPlus;
		currentHealth += healthPlus;
		
		if(currentHealth > maxHealth) {
			currentHealth = maxHealth;
		}
	}

	public float getBonusHealth() {
		return bonusHealth;
	}

	public void addBonusHealth(float bonusHealth) {
		this.bonusHealth += bonusHealth;
	}

}
