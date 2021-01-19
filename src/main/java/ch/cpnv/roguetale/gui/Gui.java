package ch.cpnv.roguetale.gui;

import java.util.ArrayList;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import ch.cpnv.roguetale.controller.Controller;
import ch.cpnv.roguetale.gui.button.GuiButton;
import ch.cpnv.roguetale.gui.texts.GuiLabel;

public class Gui implements Controller {
	protected Gui prevGui;
	protected ArrayList<GuiButton> buttonList = new ArrayList<GuiButton>();
	protected ArrayList<GuiLabel> labelList = new ArrayList<GuiLabel>();
	
	public Gui(Gui prevGui) {
		this.prevGui = prevGui;
	}
	
	public void init() throws SlickException {}
	
	public void render(GameContainer gc, Graphics g, Vector2f origin) throws SlickException {
		for (GuiButton btn : buttonList) {
			btn.render(gc, g);
		}
		for (GuiLabel label : labelList)
			label.render(gc, g);
		for (GuiButton btn : buttonList) {
			btn.renderTooltip(g);
		}
	}

	public void update(GameContainer gc, int delta, Vector2f origin) throws SlickException {
		// TODO Auto-generated method stub

	}

	public void keyReleased(int key, char c, GameContainer gc) throws SlickException {
		// TODO Auto-generated method stub

	}

	public void keyPressed(int key, char c, GameContainer gc) throws SlickException {
		// TODO Auto-generated method stub

	}

	public void mousePressed(int button, int x, int y) throws SlickException {
		for (GuiButton btn : buttonList) {
			if (btn.isHoveringButton(x, y)) {
				btn.onClick();
				break;
			}
		}

	}
	
	public void mouseMoved(int oldx, int oldy, int newx, int newy) {
		for (GuiButton btn : buttonList) {
			btn.mouseHover(oldx, oldy, newx, newy);
		}
	}

	public Gui getPrevGui() {
		return prevGui;
	}

	@Override
	public void mouseReleased(int button, int x, int y) throws SlickException {
		// TODO Auto-generated method stub
		
	}
}
