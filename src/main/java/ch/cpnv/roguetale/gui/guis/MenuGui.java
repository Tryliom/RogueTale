package ch.cpnv.roguetale.gui.guis;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import ch.cpnv.roguetale.entity.character.Player;
import ch.cpnv.roguetale.entity.character.enemy.Bomber;
import ch.cpnv.roguetale.gui.Gui;
import ch.cpnv.roguetale.gui.button.buttons.OptionButton;
import ch.cpnv.roguetale.gui.button.buttons.PlayButton;
import ch.cpnv.roguetale.main.Main;

public class MenuGui extends Gui {
	private Animation displayPlayer;
	private Animation displayBomber;
	private Image background;
	
	
	public MenuGui(Gui prevGui) {
		super(prevGui);
		try {
			this.init();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	public void init() throws SlickException {
		this.buttonList.add(new PlayButton("Jouer", Main.BASE_WIDTH/2 - 100, Main.BASE_HEIGHT*3/4, 200, 40, this));
		this.buttonList.add(new OptionButton(Main.BASE_WIDTH/2 - 100, Main.BASE_HEIGHT*5/8, 200, 40, this));
		this.displayPlayer = Player.getBaseAnimation();
		this.displayBomber = Bomber.getBaseAnimation();
		this.background = new Image("ch\\cpnv\\roguetale\\images\\background\\tile.png");
		this.background.setFilter(Image.FILTER_NEAREST);
	}
	
	public void render(GameContainer gc, Graphics g, Vector2f origin) throws SlickException {
		// Draw map
		for (int i = 0;i<gc.getWidth(); i += this.background.getWidth()) {
			for (int j = 0;j<gc.getHeight(); j += this.background.getHeight()) {
				this.background.draw(i, j);
			}
		}
		// Draw button and label
		super.render(gc, g, origin);
		// Draw sprite
		int width = this.displayPlayer.getWidth();
		int height = this.displayPlayer.getHeight();
		this.displayPlayer.draw(Main.BASE_WIDTH/2 - width/2, Main.BASE_HEIGHT/2 - height/2, width, height);
		this.displayBomber.draw(Main.BASE_WIDTH/4 - width/2, Main.BASE_HEIGHT/4 - height/2, width, height);
		this.displayBomber.draw(Main.BASE_WIDTH*3/4 - width/2, Main.BASE_HEIGHT/4 - height/2, width, height);
	}

	public void update(GameContainer gc, int delta, Vector2f origin) throws SlickException {
		super.update(gc, delta, origin);

	}

	public void keyReleased(int key, char c, GameContainer gc) throws SlickException {
		super.keyReleased(key, c, gc);

	}

	public void keyPressed(int key, char c, GameContainer gc) throws SlickException {
		super.keyPressed(key, c, gc);

	}

	public void mousePressed(int button, int x, int y) throws SlickException {
		super.mousePressed(button, x, y);

	}
	
	public void mouseMoved(int oldx, int oldy, int newx, int newy) {
		super.mouseMoved(oldx, oldy, newx, newy);
	}
}
