package main.java.ch.cpnv.roguetale.entity;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.SpriteSheet;

public abstract class Projectile extends MovableItem {
	protected int lifespan;
	
	public Projectile(SpriteSheet ss, Vector2f position, int speed, Direction direction) {
		super(ss, position, speed, direction);
	}
}
