package ch.cpnv.roguetale.entity.damageable;

public class NoDamage implements DamageStrategy {
	@Override
	public void damage(int damage) {
		// Do nothing

	}

	@Override
	public void heal(int heal) {
		// Do nothing
	}

	@Override
	public Boolean isDead() {
		return false;
	}

}
