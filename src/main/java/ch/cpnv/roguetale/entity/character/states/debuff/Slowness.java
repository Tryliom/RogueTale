package ch.cpnv.roguetale.entity.character.states.debuff;

import ch.cpnv.roguetale.entity.character.State;

public class Slowness extends State {
	private int slowness;

	/**
	 * Slow the character
	 * @param remainingTime
	 * @param slowness 			The slowness apply to character in percent
	 */
	public Slowness(int remainingTime, int slowness) {
		super("Slowness", remainingTime);
		this.slowness = slowness;
	}

	public int getSlowness() {
		return slowness;
	}
}
