package ch.cpnv.roguetale.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import ch.cpnv.roguetale.entity.Direction;
import ch.cpnv.roguetale.entity.character.Ability;
import ch.cpnv.roguetale.entity.character.Player;
import ch.cpnv.roguetale.entity.pickupableitem.PickupableItem;
import ch.cpnv.roguetale.gui.guis.GameGui;
import ch.cpnv.roguetale.main.Main;
import ch.cpnv.roguetale.save.SaveCommand;
import ch.cpnv.roguetale.save.enums.CommandType;
import ch.cpnv.roguetale.weapon.melee.Knife;
import ch.cpnv.roguetale.weapon.other.Shield;

public class PlayerController implements Controller {
	private Player player;
	private final HashMap<Integer, Direction> MOVING_KEYS = new HashMap<Integer, Direction>();
	private final HashMap<Integer, Ability> ABILITY_KEYS = new HashMap<Integer, Ability>();

	public PlayerController() throws SlickException {	
		this.player = new Player( 
				new Vector2f(0,0), 
				150, 
				Direction.DOWN, 
				false, 
				new Knife(),
				new Shield());
		this.setupKeys();
	}
	
	public void setupKeys() {
		this.MOVING_KEYS.clear();
		this.ABILITY_KEYS.clear();
		SaveCommand cmd = Main.saveController.getCommand();
		this.MOVING_KEYS.put(cmd.getKey(CommandType.Up), Direction.UP);
		this.MOVING_KEYS.put(cmd.getKey(CommandType.Left), Direction.LEFT);
		this.MOVING_KEYS.put(cmd.getKey(CommandType.Right), Direction.RIGHT);
		this.MOVING_KEYS.put(cmd.getKey(CommandType.Down), Direction.DOWN);
		
		this.ABILITY_KEYS.put(cmd.getKey(CommandType.Dash), this.player.getDash());
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
		
		if (this.ABILITY_KEYS.containsKey(key)) {
			this.ABILITY_KEYS.get(key).activate(this.player);
		}
	}
	
	@Override
	public void keyReleased(int key, char c, GameContainer gc) throws SlickException {
		// If direction key is released, check that other key are not pressed to disallowing player to move unless change direction of player
		if (this.MOVING_KEYS.containsKey(key)) {
			updateDirection(gc);
		}
	}
	
	@Override
	public void mousePressed(int button, int x, int y) throws SlickException {
		
	}
	
	@Override
	public void mouseReleased(int button, int x, int y) throws SlickException {
		int prim = Input.MOUSE_LEFT_BUTTON;
		int second = Input.MOUSE_RIGHT_BUTTON;
		
		if (prim == button) {
			player.attackWithWeapon(player.getPrimaryWeapon());
		}
		
		if (second == button) {
			player.attackWithWeapon(player.getSecondaryWeapon());
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
