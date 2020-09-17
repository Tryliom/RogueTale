package ch.cpnv.roguetale.model;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import ch.cpnv.roguetale.controller.EnemyController;
import ch.cpnv.roguetale.controller.MapController;
import ch.cpnv.roguetale.controller.PlayerController;
import ch.cpnv.roguetale.controller.ProjectileController;
import ch.cpnv.roguetale.entity.character.Player;
import ch.cpnv.roguetale.weapon.Weapon;

public class Game extends BasicGame {
	
	private GameContainer gc;
	private int width;
	private int height;
	@SuppressWarnings("unused")
	private int score;

	public Game() {
		// Title windows name
		super("RogueTale");
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		Vector2f origin = getSlickOrigin();
		Player player = PlayerController.getInstance().getPlayer();
		
		MapController.getInstance().render(gc, g, origin, player);
		EnemyController.getInstance().render(gc, g, origin, player);
		PlayerController.getInstance().render(gc, g, origin, player);
		ProjectileController.getInstance().render(gc, g, origin, player);
		
		// Define color before an action
		g.setColor(new Color(60, 60, 200));
		g.drawString("RogueTale", 0, 0);
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		this.gc = gc;
		this.height = gc.getHeight();
		this.width = gc.getWidth();
		
		Weapon.setProjectileController(ProjectileController.getInstance());
		
		// Define values
		gc.setShowFPS(false);
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		Player player = PlayerController.getInstance().getPlayer();
		
		MapController.getInstance().update(gc, delta, player);
		EnemyController.getInstance().update(gc, delta, player);
		PlayerController.getInstance().update(gc, delta, player);
		ProjectileController.getInstance().update(gc, delta, player);
	}
	
	@Override
	public void keyReleased(int key, char c) {
		try {
			PlayerController.getInstance().keyReleased(key, c, this.gc);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		if (Input.KEY_ESCAPE == key) {
			gc.exit();
		}
	}
	
	@Override
	public void keyPressed(int key, char c) {
		try {
			PlayerController.getInstance().keyPressed(key, c, this.gc);
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	@Override 
	public void mousePressed(int button, int x, int y) {
		try {
			PlayerController.getInstance().mousePressed(button, x, y);
		} catch (SlickException e) {
			e.printStackTrace();
		};
	}
	
	// Get the coordinate of the UP LEFT corner of the screen
	protected Vector2f getSlickOrigin() throws SlickException {
		Player player = PlayerController.getInstance().getPlayer();
		return new Vector2f(player.getPosition().x - this.width/2, player.getPosition().y + this.height/2);
	}

}
