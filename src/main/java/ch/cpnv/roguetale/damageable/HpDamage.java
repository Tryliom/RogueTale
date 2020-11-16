package ch.cpnv.roguetale.damageable;

public class HpDamage implements DamageStrategy {
	protected int health;
	
	public HpDamage(int maxHp) {
		this.health = maxHp;
	}

	@Override
	public void updateHealth(int health) {
		this.health += health;
	}

	@Override
	public void damage(int damage) {
		this.health -= damage;
	}

	@Override
	public void heal(int heal) {
		this.health += heal;

	}

	@Override
	public Boolean isDead() {
		return health <= 0;
	}

}
