package ch.cpnv.roguetale.controller;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class GameController {
	private PlayerController playerController;
	private MapController mapController;
	private int score;
	
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	
	public GameController(GameContainer gc) throws SlickException {
		this.playerController = new PlayerController();
		this.mapController = new MapController(gc);
	}
	
	public void update(GameContainer gc, int delta) {
		this.playerController.update(gc, delta);
		this.mapController.update(gc, this.playerController.getPlayer());
	}
	
	public void keyReleased(int key, char c, GameContainer gc) {
		this.playerController.keyReleased(key, c, gc);
		
		if (Input.KEY_ESCAPE == key) {
			gc.exit();
		}
	}
	
	public void keyPressed(int key, char c, GameContainer gc) {
		this.playerController.keyPressed(key, c, gc);
	}
	
	/* Getter & Setter */
	
	public PlayerController getPlayerController() {
		return playerController;
	}
	public void setPlayerController(PlayerController playerController) {
		this.playerController = playerController;
	}
	public MapController getMapController() {
		return mapController;
	}
	public void setMapController(MapController mapController) {
		this.mapController = mapController;
	}
	
}
