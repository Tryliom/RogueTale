package ch.cpnv.roguetale.damageable;

public interface DamageStrategy {
	public void updateHealth(int health);
	public void damage(int damage);
	public void heal(int heal);
	
	public Boolean isDead();
}
