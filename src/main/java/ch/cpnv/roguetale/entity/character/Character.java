package main.java.ch.cpnv.roguetale.entity.character;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.SpriteSheet;

import main.java.ch.cpnv.roguetale.entity.Direction;
import main.java.ch.cpnv.roguetale.entity.MovableItem;

public class Character extends MovableItem {
	protected int currentHealth;
	protected int maxHealth;
	

	public Character(SpriteSheet ss, Vector2f position, int speed, Direction direction) {
		super(ss, position, speed, direction);
		// TODO Auto-generated constructor stub
	}

}
