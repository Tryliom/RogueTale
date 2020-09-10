package ch.cpnv.roguetale.controller;

import java.util.HashMap;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import ch.cpnv.roguetale.entity.Direction;
import ch.cpnv.roguetale.entity.character.Player;
import ch.cpnv.roguetale.weapon.melee.Knife;
import ch.cpnv.roguetale.weapon.ranged.Bow;

public class PlayerController implements Controller {
	private Player player;
	private HashMap<Integer, Direction> MOVING_KEY = new HashMap<Integer, Direction>();

	public PlayerController() throws SlickException {
		this.player = new Player(
				new SpriteSheet("ch\\cpnv\\roguetale\\images\\player\\carac.png", 48, 48, 0), 
				new Vector2f(0,0), 
				100, 
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
	
	@Override
	public void render(GameContainer gc, Graphics g, Vector2f origin, Player p) throws SlickException {		
		g.setColor(new Color(200, 60, 60));
		g.drawString("Joueur", 0, 20);
		g.drawString("X: "+p.getPosition().x+", Y: "+p.getPosition().y, 0, 40);
		player.draw(origin, gc);
	}

	@Override
	public void update(GameContainer gc, int delta, Player p) throws SlickException {
		if (this.player.isMoving()) {
			this.player.move(delta);
		}
		
	}
	
	@Override
	public void keyPressed(int key, char c, GameContainer gc) {
		// If a direction key is pressed, set the direction of player and allow it to move
		if (this.MOVING_KEY.containsKey(key)) {
			this.player.setDirection(MOVING_KEY.get(key));
			this.player.setMoving(true);
		}
	}
	
	@Override
	public void keyReleased(int key, char c, GameContainer gc) {
		// If direction key is released, check that other key are not pressed to disallowing player to move unless change direction of player
		if (this.MOVING_KEY.containsKey(key)) {
			updateDirection(gc);
		}
	}
	
	public void updateDirection(GameContainer gc) {
		Boolean isStillMoving = false;
		for (int key : MOVING_KEY.keySet()) {
			if (gc.getInput().isKeyDown(key)) {
				this.player.setDirection(MOVING_KEY.get(key));
				isStillMoving = true;
			}
		}
		
		player.setMoving(isStillMoving);
	}


	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
}
