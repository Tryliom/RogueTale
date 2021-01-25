package ch.cpnv.roguetale.purchase.purchases;

import ch.cpnv.roguetale.purchase.Purchase;

public class DashCooldownReduction extends Purchase {
	private static final long serialVersionUID = -8740037684910128205L;
	private static final int[] cost = {500, 750, 1000, 1500, 2000};

	public DashCooldownReduction() {
		super("Réduit le temps de recharge du Dash", 5, cost);
	}

	@Override
	public void initDescription() {
		String desc = "Réduit de 10% le temps de recharge du Dash\n"
				+ "Total: - "+this.level*10+"% de temps de recharge du Dash";
		this.description = desc;
	}
	
	
}
