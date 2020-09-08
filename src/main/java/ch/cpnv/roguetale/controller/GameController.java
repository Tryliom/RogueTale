package ch.cpnv.roguetale.controller;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class GameController {
	private PlayerController playerController;
	private int score;
	
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	
	public GameController() throws SlickException {
		this.initMap();
		this.playerController = new PlayerController();
	}

	public void initMap() {
		
	}
	
	public void update(GameContainer gc, int delta) {
		this.playerController.update(gc, delta);
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

	
	
}
