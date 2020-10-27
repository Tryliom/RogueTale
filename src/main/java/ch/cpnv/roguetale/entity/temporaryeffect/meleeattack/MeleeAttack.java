package ch.cpnv.roguetale.entity.temporaryeffect.meleeattack;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import ch.cpnv.roguetale.entity.Direction;
import ch.cpnv.roguetale.entity.character.Character;
import ch.cpnv.roguetale.entity.temporaryeffect.TemporaryEffect;
import ch.cpnv.roguetale.weapon.MeleeWeapon;

public abstract class MeleeAttack extends TemporaryEffect {
	protected MeleeWeapon weapon;
	
	protected Direction direction;
	
	public MeleeAttack(SpriteSheet ss, Vector2f position, Direction direction, int remainingTime, MeleeWeapon weapon) {
		super(ss, position, remainingTime);
		this.weapon = weapon;
		this.direction = direction;
		this.setImageDirection();
	}
	
	public MeleeAttack(SpriteSheet ss, Character attacker, int remainingTime, MeleeWeapon weapon) {
		this(ss, attacker.getPosition(), attacker.getDirection(), remainingTime, weapon);
		setPositionFromCharacter(attacker);
	}
	
	public void meetCharacter(Character attackedCharacter) throws SlickException {
		weapon.meetCharacter(attackedCharacter);
	}
	
	protected void setImageDirection() {
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
	
	protected void setPositionFromCharacter(Character attacker) {
		Image attackerSprite = attacker.getSprite();
		
		float margin = 1;
		
		switch(attacker.getDirection()) {
		case RIGHT:
			position.x += (attackerSprite.getWidth() + getImageWidth()) / 2 + margin;
			break;
		case LEFT:
			position.x -= (attackerSprite.getWidth() + getImageWidth()) / 2 + margin;
			break;
		case UP:
			position.y += (attackerSprite.getHeight() + getImageWidth()) / 2 + margin;
			break;
		case DOWN:
			position.y -= (attackerSprite.getHeight() + getImageWidth()) / 2 + margin;
			break;
		}
	}
}
