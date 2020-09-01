package ch.cpnv.roguetale.model;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Game extends BasicGame {
	
	private GameContainer gc;
	private SpriteSheet playerAnimation;
	private SpriteSheet player;
	private int width;
	private int height;

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
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		this.gc = gc;
		this.playerAnimation = new SpriteSheet("ch\\cpnv\\roguetale\\images\\player\\spritesheet.png", 64, 64, 0);
		this.player = new SpriteSheet("ch\\cpnv\\roguetale\\images\\player\\carac.png", 48, 48, 0);
		this.height = gc.getHeight();
		this.width = gc.getWidth();
		
		
		// Define values
		gc.setShowFPS(false);
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		
	}
	
	@Override
	public void keyReleased(int key, char c) {
		if (Input.KEY_ESCAPE == key) {
			gc.exit();
		}
	}

}
