package ch.cpnv.roguetale.entity.character.states.buff;

import ch.cpnv.roguetale.entity.character.State;

public class Speed extends State {
	private int speed;

	/**
	 * 
	 * @param duration
	 * @param speed		Coefficient of speed, if 10 = 10x faster
	 */
	public Speed(int duration, int speed) {
		super("Speed", duration);
		this.speed = speed;
	}

	public int getSpeed() {
		return speed;
	}
}
