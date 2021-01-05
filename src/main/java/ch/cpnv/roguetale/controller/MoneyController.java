package ch.cpnv.roguetale.controller;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class MoneyController implements Controller {
	
	protected int money;
	
	public void addMoney(int amount) {
		money += amount;
	}
	
	public void removeMoney(int amount) {
		money -= amount;
	}

	@Override
	public void render(GameContainer gc, Graphics g, Vector2f origin) throws SlickException {
		// Do nothing
	}

	@Override
	public void update(GameContainer gc, int delta, Vector2f origin) throws SlickException {
		// Do nothing
	}

	@Override
	public void keyReleased(int key, char c, GameContainer gc) throws SlickException {
		// Do nothing
	}

	@Override
	public void keyPressed(int key, char c, GameContainer gc) throws SlickException {
		// Do nothing
	}

	@Override
	public void mousePressed(int button, int x, int y) throws SlickException {
		// Do nothing
	}

	@Override
	public void mouseMoved(int oldx, int oldy, int newx, int newy) {
		// Do nothing
	}

	@Override
	public void mouseReleased(int button, int x, int y) throws SlickException {
		// Do nothing
	}

}
