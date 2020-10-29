package ch.cpnv.roguetale.controller;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public interface Controller {	
	public void render(GameContainer gc, Graphics g, Vector2f origin) throws SlickException;
	
	public void update(GameContainer gc, int delta, Vector2f origin) throws SlickException;
	
	public void keyReleased(int key, char c, GameContainer gc) throws SlickException;
	
	public void keyPressed(int key, char c, GameContainer gc) throws SlickException;
	
	public void mousePressed(int button, int x, int y) throws SlickException;

	public void mouseMoved(int oldx, int oldy, int newx, int newy);
	
	public void mouseReleased(int button, int x, int y) throws SlickException;
}
