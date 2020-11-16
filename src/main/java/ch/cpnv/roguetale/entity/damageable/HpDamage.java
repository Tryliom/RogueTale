package ch.cpnv.roguetale.entity.damageable;

public class HpDamage implements DamageStrategy {
	protected int health;
	
	public HpDamage(int maxHp) {
		this.health = maxHp;
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
