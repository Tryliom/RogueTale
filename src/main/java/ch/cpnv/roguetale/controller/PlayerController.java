package ch.cpnv.roguetale.controller;

import java.util.HashMap;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import ch.cpnv.roguetale.entity.Direction;
import ch.cpnv.roguetale.entity.character.Player;
import ch.cpnv.roguetale.weapon.melee.Knife;
import ch.cpnv.roguetale.weapon.ranged.Bow;

public class PlayerController {
	private Player player;
	private HashMap<Integer, Direction> MOVING_KEY = new HashMap<Integer, Direction>();

	public PlayerController() throws SlickException {
		this.player = new Player(
				new SpriteSheet("ch\\cpnv\\roguetale\\images\\player\\carac.png", 48, 48, 0), 
				new Vector2f(0,0), 100, 
				Direction.DOWN, 
				false, 
				new Knife(), 
				new Bow());
		// Put Input key who equals to direction
		this.MOVING_KEY.put(Input.KEY_W, Direction.UP);
		this.MOVING_KEY.put(Input.KEY_A, Direction.LEFT);
		this.MOVING_KEY.put(Input.KEY_D, Direction.RIGHT);
		this.MOVING_KEY.put(Input.KEY_S, Direction.DOWN);
	}
	
	public void update(GameContainer gc, int delta) {
		if (this.player.isMoving()) {
			this.player.move(delta);
		}
	}
	
	public void keyPressed(int key, char c, GameContainer gc) {
		// If a direction key is pressed, set the direction of player and allow it to move
		if (this.MOVING_KEY.containsKey(key)) {
			this.player.setDirection(MOVING_KEY.get(key));
			this.player.setMoving(true);
		}
	}
	
	public void keyReleased(int key, char c, GameContainer gc) {
		// If direction key is released, check that other key are not pressed to disallowing player to move unless change direction of player
		if (this.MOVING_KEY.containsKey(key)) {
			if (!setDirectionIfPressed(gc))
				this.player.setMoving(false);
		}
	}
	
	public boolean setDirectionIfPressed(GameContainer gc) {
		for (int key : MOVING_KEY.keySet()) {
			if (gc.getInput().isKeyDown(key)) {
				this.player.setDirection(MOVING_KEY.get(key));
				return true;
			}
		}
		
		return false;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

}
