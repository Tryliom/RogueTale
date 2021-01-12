package ch.cpnv.roguetale.save;

import java.io.Serializable;
import java.util.ArrayList;

import ch.cpnv.roguetale.save.other.Purchase;

public class SavePurchase implements Serializable {
	private static final long serialVersionUID = -5700901588841937807L;
	private ArrayList<Purchase> purchases = new ArrayList<Purchase>();

	public void setDefaultData() {
		// Add purchase to list
		//this.purchases.add(new Purchase("Plus de vie", "Améliore votre vie de base", 0, 5, null, 0, 0))
	}
	
	public void setPurchases(ArrayList<Purchase> list) {
		ArrayList<Purchase> purchases = new ArrayList<Purchase>();
		
		for (Purchase p : this.purchases) {
			boolean found = false;
			
			for (Purchase p2 : list) {
				if (p.getDisplayName().equalsIgnoreCase(p2.getDisplayName())) {
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
}
