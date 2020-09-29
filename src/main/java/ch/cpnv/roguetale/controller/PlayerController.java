package ch.cpnv.roguetale.controller;

import java.util.HashMap;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import ch.cpnv.roguetale.entity.Direction;
import ch.cpnv.roguetale.entity.character.Player;
import ch.cpnv.roguetale.weapon.ranged.Bow;
import ch.cpnv.roguetale.weapon.ranged.Cannon;

public class PlayerController implements Controller {
	private static PlayerController instance = null;
	
	private Player player;
	private final HashMap<Integer, Direction> MOVING_KEYS = new HashMap<Integer, Direction>();
	
	public static PlayerController getInstance() throws SlickException {
		if(instance == null) {
			instance = new PlayerController();
		}
		return instance;
	}

	private PlayerController() throws SlickException {
		this.player = new Player(
				new SpriteSheet("ch\\cpnv\\roguetale\\images\\player\\carac.png", 48, 48, 0), 
				new Vector2f(0,0), 
				150, 
				Direction.DOWN, 
				false, 
				new Cannon(), 
				new Bow());
		// Put Input key who equals to direction
		this.MOVING_KEYS.put(Input.KEY_W, Direction.UP);
		this.MOVING_KEYS.put(Input.KEY_A, Direction.LEFT);
		this.MOVING_KEYS.put(Input.KEY_D, Direction.RIGHT);
		this.MOVING_KEYS.put(Input.KEY_S, Direction.DOWN);
	}
	
	@Override
	public void render(GameContainer gc, Graphics g, Vector2f origin) throws SlickException {		
		player.draw(origin, gc);
	}

	@Override
	public void update(GameContainer gc, int delta, Vector2f origin) throws SlickException {
		if (this.player.isMoving()) {
			this.player.move(delta);
		}
		this.player.reduceCooldown(delta);
	}
	
	@Override
	public void keyPressed(int key, char c, GameContainer gc) throws SlickException {
		// If a direction key is pressed, set the direction of player and allow it to move
		if (this.MOVING_KEYS.containsKey(key)) {
			this.player.setDirection(MOVING_KEYS.get(key));
			this.player.setMoving(true);
		}
		else if (Input.KEY_Q == key) {
			player.primaryAttack();
		}
		else if (Input.KEY_E == key) {
			player.secondaryAttack();
		}
	}
	
	@Override
	public void keyReleased(int key, char c, GameContainer gc) {
		// If direction key is released, check that other key are not pressed to disallowing player to move unless change direction of player
		if (this.MOVING_KEYS.containsKey(key)) {
			updateDirection(gc);
		}
	}
	
	@Override
	public void mousePressed(int button, int x, int y) throws SlickException {
		switch(button) {
			case Input.MOUSE_LEFT_BUTTON:
				player.primaryAttack();
				break;
			case Input.MOUSE_RIGHT_BUTTON:
				player.secondaryAttack();
				break;
		}
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
	
	protected void updateDirection(GameContainer gc) {
		Boolean isStillMoving = false;
		for (int key : MOVING_KEYS.keySet()) {
			if (gc.getInput().isKeyDown(key)) {
				this.player.setDirection(MOVING_KEYS.get(key));
				isStillMoving = true;
			}
		}
		
		player.setMoving(isStillMoving);
	}
}
