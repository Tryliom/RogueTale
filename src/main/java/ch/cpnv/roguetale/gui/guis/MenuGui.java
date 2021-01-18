package ch.cpnv.roguetale.gui.guis;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import ch.cpnv.roguetale.entity.character.Player;
import ch.cpnv.roguetale.entity.character.enemies.Bomber;
import ch.cpnv.roguetale.entity.ui.UiMoney;
import ch.cpnv.roguetale.gui.Gui;
import ch.cpnv.roguetale.gui.button.buttons.OptionButton;
import ch.cpnv.roguetale.gui.button.buttons.PlayButton;
import ch.cpnv.roguetale.gui.button.buttons.QuitButton;
import ch.cpnv.roguetale.gui.button.buttons.ShopButton;
import ch.cpnv.roguetale.main.Main;

public class MenuGui extends Gui {
	private UiMoney moneyDisplayer;
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
		int w = Main.BASE_WIDTH,
			h = Main.BASE_HEIGHT;
		this.buttonList.add(new PlayButton("Jouer", w/2, h * 5/8, this));
		this.buttonList.add(new ShopButton(w/2, h * 3/4, this));
		this.buttonList.add(new OptionButton(w - 110, h - 50, this));
		this.buttonList.add(new QuitButton(110, h - 50, this));
		moneyDisplayer = new UiMoney(w/2, h - 50);
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
		
		// Draw money
		moneyDisplayer.render(gc, g);
		
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
		moneyDisplayer.update();
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
