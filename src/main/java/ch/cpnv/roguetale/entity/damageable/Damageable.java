package ch.cpnv.roguetale.entity.damageable;

public interface Damageable {
	public void damage(int damage);
	public void heal(int heal);
	
	public Boolean isDead();
}
