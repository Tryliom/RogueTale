package main.java.ch.cpnv.roguetale.model;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import main.java.ch.cpnv.roguetale.entity.Arrow;
import main.java.ch.cpnv.roguetale.entity.Direction;
import main.java.ch.cpnv.roguetale.entity.Projectile;

public class Game extends BasicGame {
	
	private GameContainer gc;
	private SpriteSheet playerAnimation;
	private SpriteSheet player;
	private int width;
	private int height;
	
	private Projectile projectile;

	public Game() {
		// Title windows name
		super("RogueTale");
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		// Define color before an action
		g.setColor(new Color(60, 60, 200));
		g.drawString("Draw sprite", 0, 0);
		Image playerImg = this.player.getSprite(0,0);
		g.drawImage(playerImg, this.width/2 - playerImg.getWidth()/2, this.height/2 - playerImg.getHeight()/2);
		
		g.drawImage(this.projectile.getSprite(), this.projectile.getPosition().x, this.projectile.getPosition().y);
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		this.gc = gc;
		this.playerAnimation = new SpriteSheet("main\\java\\ch\\cpnv\\roguetale\\images\\player\\spritesheet.png", 64, 64, 0);
		this.player = new SpriteSheet("main\\java\\ch\\cpnv\\roguetale\\images\\player\\carac.png", 48, 48, 0);
		this.height = gc.getHeight();
		this.width = gc.getWidth();
		
		Vector2f arrowPosition = new Vector2f(this.width/2, this.height/2);
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
		// TODO move projectile
	}
	
	@Override
	public void keyReleased(int key, char c) {
		if (Input.KEY_ESCAPE == key) {
			gc.exit();
		}
	}

}
