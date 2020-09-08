package ch.cpnv.roguetale.model;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import ch.cpnv.roguetale.controller.GameController;
import ch.cpnv.roguetale.entity.Arrow;
import ch.cpnv.roguetale.entity.Direction;
import ch.cpnv.roguetale.entity.Projectile;
import ch.cpnv.roguetale.entity.character.Player;

public class Game extends BasicGame {
	
	private GameContainer gc;
	private GameController controller;
	private int width;
	private int height;
	
	private Projectile projectile;

	public Game() {
		// Title windows name
		super("RogueTale");
		
		Vector2f arrowPosition = new Vector2f(this.width/2, this.height/2);
		try {
			projectile = new Arrow(arrowPosition, Direction.UP);
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		// Define color before an action
		g.setColor(new Color(60, 60, 200));
		g.drawString("RogueTale", 0, 0);
		// Draw player
		Player player = this.controller.getPlayerController().getPlayer();
		Vector2f pos = player.getPosition();
		g.setColor(new Color(200, 60, 60));
		g.drawString("Joueur", 0, 20);
		g.drawString("X: "+pos.x+", Y: "+pos.y, 0, 40);
		Image sprite = player.getSprite();
		g.drawImage(sprite, this.width/2 - sprite.getWidth()/2, this.height/2 - sprite.getHeight()/2);
		
		Image prjectileImg = this.projectile.getSprite();
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		this.gc = gc;
		this.height = gc.getHeight();
		this.width = gc.getWidth();
		this.controller = new GameController();
		
		// Define values
		gc.setShowFPS(false);
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		this.controller.update(gc, delta);
	}
	
	@Override
	public void keyReleased(int key, char c) {
		this.controller.keyReleased(key, c, this.gc);
	}
	
	@Override
	public void keyPressed(int key, char c) {
		this.controller.keyPressed(key, c, this.gc);
	}
	
	

}
