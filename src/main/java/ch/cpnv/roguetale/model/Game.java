package ch.cpnv.roguetale.model;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import ch.cpnv.roguetale.controller.MapController;
import ch.cpnv.roguetale.controller.PlayerController;
import ch.cpnv.roguetale.entity.Arrow;
import ch.cpnv.roguetale.entity.Direction;
import ch.cpnv.roguetale.entity.Projectile;
import ch.cpnv.roguetale.entity.character.Player;

public class Game extends BasicGame {
	
	private GameContainer gc;
	private PlayerController playerController;
	private MapController mapController;
	private int width;
	private int height;
	@SuppressWarnings("unused")
	private int score;
	
	private Projectile projectile;

	public Game() {
		// Title windows name
		super("RogueTale");
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		// Define values
		Player player = this.playerController.getPlayer();
		Vector2f pos = player.getPosition();
		
		this.mapController.render(gc, g, player);
		this.playerController.render(gc, g, player);
		
		// Define color before an action
		g.setColor(new Color(60, 60, 200));
		g.drawString("RogueTale", 0, 0);
		// Origin
		Vector2f origin = new Vector2f(pos.x - this.width/2, pos.y + this.height/2);
		
		// Draw projectile
		g.drawString("X: " + projectile.getPosition().x + ", Y: " + projectile.getPosition().y, 0, 80);
		g.drawString("Origin (" + origin.x + ", " + origin.y + ")", 0, 100);
		g.drawString("Position (" + (projectile.getPosition().x - origin.x) + ", " + (projectile.getPosition().y - origin.y) + ")", 0, 120);
		projectile.draw(origin);
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		this.gc = gc;
		this.height = gc.getHeight();
		this.width = gc.getWidth();
		this.playerController = new PlayerController();
		this.mapController = new MapController(gc);
		
		Vector2f arrowPosition = new Vector2f(10, -100);
		try {
			projectile = new Arrow(arrowPosition, Direction.UP);
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Define values
		gc.setShowFPS(false);
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		Player p = this.playerController.getPlayer();
		this.projectile.move(delta);
		this.playerController.update(gc, delta, p);
		this.mapController.update(gc, delta, p);
	}
	
	@Override
	public void keyReleased(int key, char c) {
		this.playerController.keyReleased(key, c, this.gc);
		
		if (Input.KEY_ESCAPE == key) {
			gc.exit();
		}
	}
	
	@Override
	public void keyPressed(int key, char c) {
		this.playerController.keyPressed(key, c, this.gc);
	}
}
