package ch.cpnv.roguetale.purchase.purchases;

import ch.cpnv.roguetale.purchase.Purchase;

public class HealthPlus extends Purchase {
	private static final long serialVersionUID = -2280229333594680273L;
	private static final int[] cost = {100, 200, 300, 400, 500, 750, 1000, 1250, 1500, 2000};

	public HealthPlus() {
		super("Améliore votre vie", 10, cost);
	}

	@Override
	public void initDescription() {
		String desc = "Ajoute un bonus de + 5% de vie maximum\n"
				+ "Total: + "+this.level * 5+"% de vie max";
		this.description = desc;
	}
	
	
}
