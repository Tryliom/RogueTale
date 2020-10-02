package ch.cpnv.roguetale.main;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.ScalableGame;
import org.newdawn.slick.SlickException;

import ch.cpnv.roguetale.model.Game;

public class Main {

	public static AppGameContainer app;
	public static int BASE_WIDTH = 640;
	public static int BASE_HEIGHT = 480;
	
	public static void main(String[] args) throws SlickException {
		ScalableGame scalable = new ScalableGame(new Game(), BASE_WIDTH, BASE_HEIGHT, false);
		app = new AppGameContainer(scalable);
		app.setDisplayMode(BASE_WIDTH*2, BASE_HEIGHT*2, false);
		app.start();
	}

}
