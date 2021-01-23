package ch.cpnv.roguetale.save.other.purchases;

import ch.cpnv.roguetale.save.other.Purchase;

public class SellWeapon extends Purchase {
	private static final long serialVersionUID = 8410019438663658529L;
	private static final int[] cost = {3000, 7500, 12000, 16000, 20000};

	public SellWeapon() {
		super("Vendre les armes", 5, cost);
	}

	@Override
	public void initDescription() {
		String desc = "Permet de vendre les armes ramassées pour 30 pièces\n"
				+ "Total: + "+this.level*30+" pièces par arme";
		this.description = desc;
	}
	
	
}
