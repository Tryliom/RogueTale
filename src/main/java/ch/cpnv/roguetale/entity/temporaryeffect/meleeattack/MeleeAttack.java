package ch.cpnv.roguetale.entity.temporaryeffect.meleeattack;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import ch.cpnv.roguetale.entity.Direction;
import ch.cpnv.roguetale.entity.character.Character;
import ch.cpnv.roguetale.entity.temporaryeffect.TemporaryEffect;
import ch.cpnv.roguetale.weapon.MeleeWeapon;

public abstract class MeleeAttack extends TemporaryEffect {
	protected MeleeWeapon weapon;
	
	public MeleeAttack(SpriteSheet ss, Vector2f position, Direction direction, int remainingTime, MeleeWeapon weapon) {
		super(ss, position, remainingTime);
		this.setImageDirection(direction);
		this.weapon = weapon;
	}
	
	public void meetCharacter(Character attackedCharacter) throws SlickException {
		weapon.meetCharacter(attackedCharacter);
	}
	
	protected void setImageDirection(Direction direction) {
		switch (direction) {
			case UP:
				this.image.rotate(-90);
				break;
			case DOWN:
				this.image.rotate(90);
				break;
			case LEFT:
				this.image.rotate(180);
				break;
			case RIGHT:
				break;
		}
	}
}
