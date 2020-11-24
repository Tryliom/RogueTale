package ch.cpnv.roguetale.entity.character.states.buff;

import ch.cpnv.roguetale.entity.Direction;
import ch.cpnv.roguetale.entity.character.State;

public class Reflection extends State {
	private Direction protect;
	private Direction reflectDirection;
	
	/**
	 * 
	 * @param remainingTime
	 * @param direction			The protected direction, automatically choose the opposite (Projectile go against)
	 */
	public Reflection(int remainingTime, Direction direction) {
		super("Reflection", remainingTime);
		this.reflectDirection = direction;
		
		switch(direction) {
			case DOWN:
				this.protect = Direction.UP;
				break;
			case LEFT:
				this.protect = Direction.RIGHT;
				break;
			case RIGHT:
				this.protect = Direction.LEFT;
				break;
			case UP:
				this.protect = Direction.DOWN;
				break;
		}
		
	}

	public Direction getProtect() {
		return protect;
	}

	public Direction getReflectDirection() {
		return reflectDirection;
	}

	
}
