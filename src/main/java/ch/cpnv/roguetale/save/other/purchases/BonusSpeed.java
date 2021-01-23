package ch.cpnv.roguetale.save.other.purchases;

import ch.cpnv.roguetale.save.other.Purchase;

public class BonusSpeed extends Purchase {
	private static final long serialVersionUID = 6107897173427948360L;
	private static final int[] cost = {1000, 2000, 4000, 8000, 15000};

	public BonusSpeed() {
		super("Améliore votre vitesse", 5, cost);
	}

	@Override
	public void initDescription() {
		String desc = "Améliore votre vitesse de 1% par niveaux\n"
				+ "Total: + "+this.level+"% de vitesse par niveaux";
		this.description = desc;
	}
	
	
}
