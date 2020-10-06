package ch.cpnv.roguetale.gui.guis;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import ch.cpnv.roguetale.controller.GuiController;
import ch.cpnv.roguetale.gui.Execute;
import ch.cpnv.roguetale.gui.Gui;
import ch.cpnv.roguetale.gui.GuiButton;
import ch.cpnv.roguetale.gui.GuiLabel;
import ch.cpnv.roguetale.main.Main;

public class GameOverGui extends Gui {

	public GameOverGui(Gui prevGui) {
		super(prevGui);
		this.init();
	}
	
	private void init() {
		Execute restartGame = () -> {GuiController.getInstance().setDisplayGui(new GameGui(this));};
		Execute returnToMenu = () -> {GuiController.getInstance().setDisplayGui(new MenuGui(this));};
		
		this.buttonList.add(new GuiButton("Rejouer", Main.BASE_WIDTH/2 - 100, Main.BASE_HEIGHT*5/8, 200, 40, restartGame));
		this.buttonList.add(new GuiButton("Retour au menu", Main.BASE_WIDTH/2 - 100, Main.BASE_HEIGHT*3/4, 200, 40, returnToMenu));
		this.labelList.add(new GuiLabel("Game Over", Main.BASE_WIDTH/2, Main.BASE_HEIGHT/4, new Color(190, 83, 83)));
	}
	
	public void render(GameContainer gc, Graphics g, Vector2f origin) throws SlickException {
		this.prevGui.render(gc, g, origin);
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
