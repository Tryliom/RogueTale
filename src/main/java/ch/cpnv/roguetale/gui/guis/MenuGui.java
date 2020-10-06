package ch.cpnv.roguetale.gui.guis;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import ch.cpnv.roguetale.controller.GuiController;
import ch.cpnv.roguetale.gui.Execute;
import ch.cpnv.roguetale.gui.Gui;
import ch.cpnv.roguetale.gui.button.GuiButton;
import ch.cpnv.roguetale.main.Main;

public class MenuGui extends Gui {
	private Animation displayPlayer;
	
	
	public MenuGui(Gui prevGui) {
		super(prevGui);
		try {
			this.init();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	private void init() throws SlickException {
		Execute startGame = () -> {
			GuiController.getInstance().setDisplayGui(new GameGui(this));
		};
		this.buttonList.add(new GuiButton("Start", Main.BASE_WIDTH/2 - 100, Main.BASE_HEIGHT*3/4, 200, 40, startGame));
		this.displayPlayer = new Animation(new SpriteSheet("ch\\cpnv\\roguetale\\images\\player\\carac.png", 48, 48, 0), 0, 0, 2, 0, true, 300, true);
	}
	
	public void render(GameContainer gc, Graphics g, Vector2f origin) throws SlickException {
		super.render(gc, g, origin);
		int width = this.displayPlayer.getWidth();
		int height = this.displayPlayer.getHeight();
		this.displayPlayer.draw(Main.BASE_WIDTH/2 - width/2, Main.BASE_HEIGHT/2 - height/2, width, height);
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
