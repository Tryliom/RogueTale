package ch.cpnv.roguetale.controller;

import java.util.Vector;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import ch.cpnv.roguetale.entity.character.Player;

public class MapController implements Controller {
	private Image background;
	private final Vector<Vector2f> map = new Vector<Vector2f>();
	private static final int TILE_DIMENSION = 70;
	
	public MapController() throws SlickException {
		this.background = new Image("ch\\cpnv\\roguetale\\images\\background\\tile.png");
	}
	
	@Override
	public void render(GameContainer gc, Graphics g, Vector2f origin, Player p) {
		Image bg = this.getBackground();
		Vector<Vector2f> map = this.getMap();
		Vector2f pos = p.getPosition();
		int height = gc.getHeight();
		int width = gc.getWidth();
		int doubleChunk = TILE_DIMENSION*2;
		int minScreenX = Math.round(pos.x) - width/2 - doubleChunk;
		int maxScreenX = Math.round(pos.x) + width/2 + doubleChunk;
		int minScreenY = Math.round(pos.y) - height/2 - doubleChunk;
		int maxScreenY = Math.round(pos.y) + height/2 + doubleChunk;
		// Draw background
		for (Vector2f vector : map) {
			// Multiply by 70 for image dimension
			float posMapX = vector.x*TILE_DIMENSION;
			float posMapY = vector.y*TILE_DIMENSION;
			float tilePosXDiff = posMapX - Math.round(pos.x);
			float tilePosYDiff = posMapY - Math.round(pos.y);
			
			if (minScreenX < posMapX && maxScreenX > posMapX && minScreenY < posMapY && maxScreenY > posMapY)
				g.drawImage(bg, tilePosXDiff + width/2, -tilePosYDiff + height/2);
		}
	}
	
	@Override
	public void update(GameContainer gc, int delta, Player player) {
		Vector2f pos = player.getPosition();
		int y = Math.round(pos.getY());
		int x = Math.round(pos.getX());
		int minHeight = - gc.getHeight()/2 + y;
		int minWidth = - gc.getWidth()/2 + x;
		int maxHeight = gc.getHeight()/2 + y;
		int maxWidth = gc.getWidth()/2 + x;
		
		for (int h = minHeight/TILE_DIMENSION - 2; h < maxHeight/TILE_DIMENSION + 2; h++) {
			for (int w = minWidth/TILE_DIMENSION - 2; w < maxWidth/TILE_DIMENSION + 2; w++) {
				Vector2f newPos = new Vector2f(w, h);
				// Check to not add duplicata
				if (!this.map.contains(newPos)) {
					this.map.add(newPos);
				}
			}
		}
	}
	
	@Override
	public void keyReleased(int key, char c, GameContainer gc) {
		// Nothing to do
	}

	@Override
	public void keyPressed(int key, char c, GameContainer gc) {
		// Nothing to do
	}
	
	@Override
	public void mousePressed(int button, int x, int y) {
		// Nothing to do
	}
	
	public Image getBackground() {
		return background;
	}

	public Vector<Vector2f> getMap() {
		return map;
	}
	
}
