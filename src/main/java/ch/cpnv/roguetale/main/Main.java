package ch.cpnv.roguetale.main;

import java.io.IOException;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.ScalableGame;
import org.newdawn.slick.SlickException;

import ch.cpnv.roguetale.model.Game;
import ch.cpnv.roguetale.save.SaveController;
import ch.cpnv.roguetale.save.SaveGraphic;

public class Main {
	public static AppGameContainer app;
	public static int BASE_WIDTH = (int) 1920/2;
	public static int BASE_HEIGHT = (int) 1080/2;
	public static SaveController saveController = new SaveController();
	
	public static void main(String[] args) throws SlickException, ClassNotFoundException, IOException {
		ScalableGame scalable = new ScalableGame(new Game(), BASE_WIDTH, BASE_HEIGHT, false);
		app = new AppGameContainer(scalable);
		saveController.loadAll();
		SaveGraphic graph = saveController.getGraphic();
		app.setDisplayMode(Math.round(graph.getResolution().x), Math.round(graph.getResolution().y), graph.getFullscreen());
		app.start();
	}

}
