package ch.cpnv.roguetale.model;

import java.awt.Font;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;

import ch.cpnv.roguetale.controller.GuiController;

public class Game extends BasicGame {
	private static Game instance;
	private GameContainer gc;
	private Font font;
	private TrueTypeFont ttf;
	
	@SuppressWarnings("unused")
	private int score;

	public Game() {
		// Title windows name
		super("RogueTale");
		instance = this;
	}

	public static Game getInstance() {
		return instance;
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		this.gc = gc;
		
		gc.setShowFPS(false);
		
		GuiController.getInstance().init();
		font = new Font("Century Gothic", Font.BOLD, 18);
		ttf = new TrueTypeFont(font, true);
	}
	

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		g.setFont(ttf);
		g.setAntiAlias(true);
		GuiController.getInstance().render(gc, g, null);
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		GuiController.getInstance().update(gc, delta, null);
	}
	
	@Override
	public void keyReleased(int key, char c) {
		try {
			GuiController.getInstance().keyReleased(key, c, gc);
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void keyPressed(int key, char c) {
		try {
			GuiController.getInstance().keyPressed(key, c, gc);
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	@Override 
	public void mousePressed(int button, int x, int y) {
		try {
			GuiController.getInstance().mousePressed(button, x, y);
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	@Override 
	public void mouseMoved(int oldx, int oldy, int newx, int newy) {
		GuiController.getInstance().mouseMoved(oldx, oldy, newx, newy);
	}

	public GameContainer getGc() {
		return gc;
	}

	
}
