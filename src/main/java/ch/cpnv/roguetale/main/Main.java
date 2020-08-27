package ch.cpnv.roguetale.main;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

import ch.cpnv.roguetale.model.Game;

public class Main {

	static private AppGameContainer app;
	
	public static void main(String[] args) throws SlickException {
		app = new AppGameContainer( new Game() );
		app.setDisplayMode(640, 480, false);
		app.start();
	}

}
