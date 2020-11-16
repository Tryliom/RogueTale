package ch.cpnv.roguetale.damageable;

public class NoDamage implements DamageStrategy {

	@Override
	public void updateHealth(int health) {
		// Do nothing
	}

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
