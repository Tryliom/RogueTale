package ch.cpnv.roguetale.entity.character.states.buff;

import ch.cpnv.roguetale.entity.character.State;

public class CancelProjectiles extends State {
	private int luckPercent;

	public CancelProjectiles(int remainingTime, int luckPercent) {
		super("Cancel Projectiles", remainingTime);
		this.luckPercent = luckPercent;
	}

	public int getLuckPercent() {
		return luckPercent;
	}
}
