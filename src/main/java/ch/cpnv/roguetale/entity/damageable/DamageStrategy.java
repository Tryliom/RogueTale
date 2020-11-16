package ch.cpnv.roguetale.entity.damageable;

public interface DamageStrategy {
	public void damage(int damage);
	public void heal(int heal);
	
	public Boolean isDead();
}
