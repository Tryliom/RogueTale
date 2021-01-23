package ch.cpnv.roguetale.save;

import java.io.Serializable;
import java.util.ArrayList;

import ch.cpnv.roguetale.save.other.Purchase;
import ch.cpnv.roguetale.save.other.purchases.BonusSpeed;
import ch.cpnv.roguetale.save.other.purchases.DashCooldownReduction;
import ch.cpnv.roguetale.save.other.purchases.HUDEnemyLevel;
import ch.cpnv.roguetale.save.other.purchases.HUDEnemyLife;
import ch.cpnv.roguetale.save.other.purchases.HealthPlus;
import ch.cpnv.roguetale.save.other.purchases.MidasTouch;
import ch.cpnv.roguetale.save.other.purchases.SellWeapon;

public class SavePurchase implements Serializable {
	private static final long serialVersionUID = 5700901588841937807L;
	private ArrayList<Purchase> purchases = new ArrayList<Purchase>();

	public SavePurchase() {
		this.setDefaultData();
	}
	
	public void setDefaultData() {
		this.purchases.add(new HealthPlus());
		this.purchases.add(new BonusSpeed());
		this.purchases.add(new DashCooldownReduction());
		this.purchases.add(new HUDEnemyLevel());
		this.purchases.add(new HUDEnemyLife());
		this.purchases.add(new SellWeapon());
		this.purchases.add(new MidasTouch());
	}
	
	public void setPurchases(ArrayList<Purchase> list) {
		ArrayList<Purchase> purchases = new ArrayList<Purchase>();
		
		for (Purchase p : this.purchases) {
			boolean found = false;
			
			for (Purchase p2 : list) {
				if (p.getClass().getName() == p2.getClass().getName()) {
					found = true;
					purchases.add(p2);
				}
			}
			
			if (!found) {
				purchases.add(p);
			}
		}
		
		this.purchases = purchases;
	}

	public ArrayList<Purchase> getPurchases() {
		return purchases;
	}
	
	public Purchase getPurchase(Class<?> cls) {
		for (Purchase p : this.purchases) {
			
			if (cls.isInstance(p)) {
				return p;
			}
		}
		
		return null;
	}
}
