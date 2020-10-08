package ch.cpnv.roguetale.main;

import java.io.IOException;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.ScalableGame;
import org.newdawn.slick.SlickException;

import ch.cpnv.roguetale.model.Game;
import ch.cpnv.roguetale.save.SaveData;
import ch.cpnv.roguetale.save.SaveManager;

public class Main {
	public static AppGameContainer app;
	public static int BASE_WIDTH = (int) 1920/3;
	public static int BASE_HEIGHT = (int) 1080/3;
	public static SaveData data = new SaveData();
	
	public static void main(String[] args) throws SlickException, ClassNotFoundException, IOException {
		ScalableGame scalable = new ScalableGame(new Game(), BASE_WIDTH, BASE_HEIGHT, false);
		app = new AppGameContainer(scalable);
		new SaveManager().loadSettings();
		app.setDisplayMode(Math.round(data.getResolution().x), Math.round(data.getResolution().y), data.getFullscreen());
		app.start();
	}

}
