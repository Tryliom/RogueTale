package main.java.ch.cpnv.roguetale.model;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

import main.java.ch.cpnv.roguetale.entity.character.Player;

public class GameController {
	private Player player;
	private int score;
	
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	
	public GameController() {
		this.initMap();
	}

	public void initMap() {
		
	}
	
	public void update(GameContainer gc, int delta) {
		
	}
	
	public void keyReleased(int key, char c, GameContainer gc) {
		if (Input.KEY_ESCAPE == key) {
			gc.exit();
		}
	}

}
