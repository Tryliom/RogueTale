package ch.cpnv.roguetale.main;

import java.awt.Dimension;
import java.awt.Toolkit;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.ScalableGame;
import org.newdawn.slick.SlickException;

import ch.cpnv.roguetale.model.Game;

public class Main {
	private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	public static AppGameContainer app;
	public static int BASE_WIDTH = (int) screenSize.getWidth()/3;
	public static int BASE_HEIGHT = (int) (screenSize.getHeight())/3;
	
	public static void main(String[] args) throws SlickException {
		ScalableGame scalable = new ScalableGame(new Game(), BASE_WIDTH, BASE_HEIGHT, false);
		app = new AppGameContainer(scalable);
		app.setDisplayMode(BASE_WIDTH, BASE_HEIGHT, false);
		app.start();
	}

}
