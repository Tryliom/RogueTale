package ch.cpnv.roguetale.entity.damageable;

public class OneHitDamage implements DamageStrategy {
	protected boolean isDead = false;

	@Override
	public void damage(int damage) {
		isDead = true;

	}

	@Override
	public void heal(int heal) {
		// Do nothing
	}

	@Override
	public Boolean isDead() {
		return isDead;
	}

}
