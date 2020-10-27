package ch.cpnv.roguetale.entity.temporaryeffect.meleeattack;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.SpriteSheet;

import ch.cpnv.roguetale.entity.temporaryeffect.TemporaryEffect;

public abstract class MeleeAttack extends TemporaryEffect {
	public MeleeAttack(SpriteSheet ss, Vector2f position, int remainingTime) {
		super(ss, position, remainingTime);
	}
}
