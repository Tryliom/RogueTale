package ch.cpnv.roguetale.gui.guis;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import ch.cpnv.roguetale.controller.GuiController;
import ch.cpnv.roguetale.gui.Execute;
import ch.cpnv.roguetale.gui.Gui;
import ch.cpnv.roguetale.gui.button.GuiButton;
import ch.cpnv.roguetale.main.Main;

public class MenuGui extends Gui {

	public MenuGui(Gui prevGui) {
		super(prevGui);
		this.init();
	}

	private void init() {
		Execute startGame = () -> {
			GuiController.getInstance().setDisplayGui(new GameGui(this));
		};
		this.buttonList.add(new GuiButton("Start", Main.BASE_WIDTH/2 - 100, Main.BASE_HEIGHT*3/4, 200, 40, startGame));
	}
	
	public void render(GameContainer gc, Graphics g, Vector2f origin) throws SlickException {
		super.render(gc, g, origin);
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
