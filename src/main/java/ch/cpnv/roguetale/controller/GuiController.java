package ch.cpnv.roguetale.controller;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import ch.cpnv.roguetale.gui.Gui;
import ch.cpnv.roguetale.gui.guis.MenuGui;

public class GuiController implements Controller {
	private Gui currentGui;
	private static GuiController instance;
	
	public static GuiController getInstance() {
		return instance == null ? instance = new GuiController() : instance;
	}
	
	private GuiController() {}
	
	public void init() {
		this.currentGui = new MenuGui(null);
	}
	
	@Override
	public void render(GameContainer gc, Graphics g, Vector2f origin) throws SlickException {
		this.currentGui.render(gc, g, origin);
	}

	@Override
	public void update(GameContainer gc, int delta, Vector2f origin) throws SlickException {
		this.currentGui.update(gc, delta, origin);

	}

	@Override
	public void keyReleased(int key, char c, GameContainer gc) throws SlickException {
		this.currentGui.keyReleased(key, c, gc);

	}

	@Override
	public void keyPressed(int key, char c, GameContainer gc) throws SlickException {
		this.currentGui.keyPressed(key, c, gc);

	}

	@Override
	public void mousePressed(int button, int x, int y) throws SlickException {
		this.currentGui.mousePressed(button, x, y);
	}
	
	@Override 
	public void mouseMoved(int oldx, int oldy, int newx, int newy) {
		this.currentGui.mouseMoved(oldx, oldy, newx, newy);
	}
	
	public void setDisplayGui(Gui gui) {
		this.currentGui = gui;
	}

}
