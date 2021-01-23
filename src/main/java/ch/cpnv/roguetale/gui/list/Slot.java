package ch.cpnv.roguetale.gui.list;

import java.util.ArrayList;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import ch.cpnv.roguetale.controller.Controller;
import ch.cpnv.roguetale.gui.button.GuiButton;
import ch.cpnv.roguetale.gui.texts.GuiLabel;

public class Slot implements Controller{
	private ArrayList<GuiButton> buttonList = new ArrayList<GuiButton>();
	private ArrayList<GuiLabel> labelList = new ArrayList<GuiLabel>();
	
	public Slot(ArrayList<GuiButton> buttonList, ArrayList<GuiLabel> labelList) {
		if (buttonList != null)
			this.buttonList = buttonList;
		if (labelList != null)
			this.labelList = labelList;
	}
	
	public void render(GameContainer gc, Graphics g, int y) throws SlickException {
		for (GuiButton btn : buttonList) {
			btn.setY(y);
			btn.render(gc, g);
		}
		for (GuiLabel label : labelList) {
			label.setY(y);
			label.render(gc, g);
		}
	}

	public void renderTooltip(Graphics g) {
		for (GuiButton btn : buttonList) {
			btn.renderTooltip(g);
		}
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

	@Override
	public void render(GameContainer gc, Graphics g, Vector2f origin) throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(GameContainer gc, int delta, Vector2f origin) throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(int key, char c, GameContainer gc) throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(int key, char c, GameContainer gc) throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(int button, int x, int y) throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseWheelMoved(int score) {
		
	}
}
