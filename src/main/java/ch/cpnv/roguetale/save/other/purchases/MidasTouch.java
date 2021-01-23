package ch.cpnv.roguetale.save.other.purchases;

import ch.cpnv.roguetale.save.other.Purchase;

public class MidasTouch extends Purchase {
	private static final long serialVersionUID = 7936191890826853279L;
	private static final int[] cost = {2000, 5000, 10000, 15000, 20000};

	public MidasTouch() {
		super("Vendre la vie supplémentaire", 5, cost);
	}

	@Override
	public void initDescription() {
		String desc = "Gagne 10 pièces tous les coeurs supplémentaires ramassés\n"
				+ "Total: + "+this.level*10+" pièces par coeur supplémentaire";
		this.description = desc;
	}
	
	
}
