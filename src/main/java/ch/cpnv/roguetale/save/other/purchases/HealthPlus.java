package ch.cpnv.roguetale.save.other.purchases;

import ch.cpnv.roguetale.save.other.Purchase;

public class HealthPlus extends Purchase {
	private static final long serialVersionUID = -2280229333594680273L;
	private static final int[] cost = {1000, 1300, 1700, 2000, 2500};

	public HealthPlus() {
		super("Améliore votre vie", 5, cost);
	}

	@Override
	public void initDescription() {
		String desc = "Améliore votre vie de base de + 1 coeur\n"
				+ "Total: + "+this.level+" coeurs de base";
		this.description = desc;
	}
	
	
}
