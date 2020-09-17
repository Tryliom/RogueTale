package ch.cpnv.roguetale.controller;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import ch.cpnv.roguetale.entity.character.Player;

public interface Controller {
	public void render(GameContainer gc, Graphics g, Vector2f origin) throws SlickException;
	
	public void update(GameContainer gc, int delta, Player p) throws SlickException;
	
	public void keyReleased(int key, char c, GameContainer gc);
	
	public void keyPressed(int key, char c, GameContainer gc);
	
	public void mousePressed(int button, int x, int y);
}
