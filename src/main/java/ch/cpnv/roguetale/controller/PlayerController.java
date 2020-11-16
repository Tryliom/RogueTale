package ch.cpnv.roguetale.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import ch.cpnv.roguetale.entity.Direction;
import ch.cpnv.roguetale.entity.character.Player;
import ch.cpnv.roguetale.entity.pickupableitem.PickupableItem;
import ch.cpnv.roguetale.gui.guis.GameGui;
import ch.cpnv.roguetale.weapon.melee.Knife;
import ch.cpnv.roguetale.weapon.ranged.Bow;

public class PlayerController implements Controller {
	private Player player;
	private final HashMap<Integer, Direction> MOVING_KEYS = new HashMap<Integer, Direction>();

	public PlayerController() throws SlickException {
		// Put Input key who equals to direction
		this.MOVING_KEYS.put(Input.KEY_W, Direction.UP);
		this.MOVING_KEYS.put(Input.KEY_A, Direction.LEFT);
		this.MOVING_KEYS.put(Input.KEY_D, Direction.RIGHT);
		this.MOVING_KEYS.put(Input.KEY_S, Direction.DOWN);
		this.MOVING_KEYS.put(Input.KEY_UP, Direction.UP);
		this.MOVING_KEYS.put(Input.KEY_LEFT, Direction.LEFT);
		this.MOVING_KEYS.put(Input.KEY_RIGHT, Direction.RIGHT);
		this.MOVING_KEYS.put(Input.KEY_DOWN, Direction.DOWN);
		this.player = new Player( 
				new Vector2f(0,0), 
				150, 
				Direction.DOWN, 
				false, 
				new Knife(),
				new Bow());
	}
	
	@Override
	public void render(GameContainer gc, Graphics g, Vector2f origin) throws SlickException {		
		player.draw(origin, gc);
	}

	@Override
	public void update(GameContainer gc, int delta, Vector2f origin) throws SlickException {
		if (this.player.isMoving()) {
			this.player.move(delta, true);
		}
		pickup();
		this.player.update(delta);
		this.player.reduceCooldown(delta);
		
		if (gc.getInput().isMouseButtonDown(0)) {
			this.player.aimWeapon(this.player.getPrimaryWeapon(), delta);
		}
		
		if (gc.getInput().isMouseButtonDown(1)) {
			this.player.aimWeapon(this.player.getSecondaryWeapon(), delta);
		}
	}
	
	@Override
	public void keyPressed(int key, char c, GameContainer gc) throws SlickException {
		// If a direction key is pressed, set the direction of player and allow it to move
		if (this.MOVING_KEYS.containsKey(key)) {
			this.player.setDirection(MOVING_KEYS.get(key));
			this.player.setMoving(true);
		}
	}
	
	@Override
	public void keyReleased(int key, char c, GameContainer gc) throws SlickException {
		// If direction key is released, check that other key are not pressed to disallowing player to move unless change direction of player
		if (this.MOVING_KEYS.containsKey(key)) {
			updateDirection(gc);
		} else if (Input.KEY_Q == key) {
			player.attackWithWeapon(player.getPrimaryWeapon());
		} else if (Input.KEY_E == key) {
			player.attackWithWeapon(player.getSecondaryWeapon());
		}
	}
	
	@Override
	public void mousePressed(int button, int x, int y) throws SlickException {
		
	}
	
	@Override
	public void mouseReleased(int button, int x, int y) throws SlickException {
		switch(button) {
			case Input.MOUSE_LEFT_BUTTON:
				player.attackWithWeapon(player.getPrimaryWeapon());
				break;
			case Input.MOUSE_RIGHT_BUTTON:
				player.attackWithWeapon(player.getSecondaryWeapon());
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
	
	protected void pickup() throws SlickException {
		ArrayList<PickupableItem> items = GameGui.getPickupableItemController().getPickupableItems();
		
		for(int i = 0; i < items.size(); i++) {
			PickupableItem item = items.get(i);
			if(player.isColliding(item)) {
				item.pickup(player);
			}
		}
	}

	@Override
	public void mouseMoved(int oldx, int oldy, int newx, int newy) {
		// TODO Auto-generated method stub
		
	}
}
