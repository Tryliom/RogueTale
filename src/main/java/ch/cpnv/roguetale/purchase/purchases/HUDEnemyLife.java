package ch.cpnv.roguetale.purchase.purchases;

import ch.cpnv.roguetale.purchase.Purchase;

public class HUDEnemyLife extends Purchase {
	private static final long serialVersionUID = 8062409733468045402L;
	private static final int[] cost = {2000};

	public HUDEnemyLife() {
		super("Vision de la vie des ennemis", 1, cost);
	}

	@Override
	public void initDescription() {
		String desc = "Permet de voir la vie des ennemis";
		this.description = desc;
	}
	
	
}
