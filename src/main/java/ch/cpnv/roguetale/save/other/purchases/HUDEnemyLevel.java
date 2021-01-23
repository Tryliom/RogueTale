package ch.cpnv.roguetale.save.other.purchases;

import ch.cpnv.roguetale.save.other.Purchase;

public class HUDEnemyLevel extends Purchase {
	private static final long serialVersionUID = -7811511646981264868L;
	private static final int[] cost = {2000};

	public HUDEnemyLevel() {
		super("Vision du niveau des ennemis", 1, cost);
	}

	@Override
	public void initDescription() {
		String desc = "Permet de voir le niveau des ennemis";
		this.description = desc;
	}
	
	
}
