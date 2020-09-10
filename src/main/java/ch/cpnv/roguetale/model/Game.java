package ch.cpnv.roguetale.model;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import ch.cpnv.roguetale.controller.GameController;
import ch.cpnv.roguetale.entity.Direction;
import ch.cpnv.roguetale.entity.character.Player;
import ch.cpnv.roguetale.entity.projectile.Arrow;
import ch.cpnv.roguetale.entity.projectile.Projectile;

public class Game extends BasicGame {
	
	private GameContainer gc;
	private GameController controller;
	private int width;
	private int height;
	
	private Projectile projectile;

	public Game() {
		// Title windows name
		super("RogueTale");
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		// Define values
		Player player = this.controller.getPlayerController().getPlayer();
		Vector2f pos = player.getPosition();
		
		this.controller.getMapController().render(gc, g, player);
		
		// Define color before an action
		g.setColor(new Color(60, 60, 200));
		g.drawString("RogueTale", 0, 0);
		// Origin
		Vector2f origin = getSlickOrigin();
		
		// Draw objects
		// Draw player
		g.setColor(new Color(200, 60, 60));
		g.drawString("Joueur", 0, 20);
		g.drawString("X: "+pos.x+", Y: "+pos.y, 0, 40);
		player.draw(origin);
		
		// Draw projectile
		projectile.draw(origin);
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		this.gc = gc;
		this.height = gc.getHeight();
		this.width = gc.getWidth();
		this.controller = new GameController(gc);
		
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
		this.projectile.move(delta);
		this.controller.update(gc, delta);
		//Player player = this.controller.getPlayerController().getPlayer(); 
		//System.out.println("Player (" + player.getPosition().x + ", " + player.getPosition().y + ")");
	}
	
	@Override
	public void keyReleased(int key, char c) {
		this.controller.keyReleased(key, c, this.gc);
	}
	
	@Override
	public void keyPressed(int key, char c) {
		this.controller.keyPressed(key, c, this.gc);
	}
	
	// Get the coordinate of the UP LEFT corner of the screen
	protected Vector2f getSlickOrigin() {
		Player player = controller.getPlayerController().getPlayer();
		return new Vector2f(player.getPosition().x - this.width/2, player.getPosition().y + this.height/2);
	}

}
