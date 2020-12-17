package ch.cpnv.roguetale.gui.guis;

import java.util.HashMap;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import ch.cpnv.roguetale.controller.GuiController;
import ch.cpnv.roguetale.gui.Gui;
import ch.cpnv.roguetale.gui.GuiUtils;
import ch.cpnv.roguetale.gui.button.GuiButton;
import ch.cpnv.roguetale.gui.button.buttons.ChangeKeyButton;
import ch.cpnv.roguetale.gui.button.buttons.ReturnButton;
import ch.cpnv.roguetale.gui.texts.GuiLabel;
import ch.cpnv.roguetale.main.Main;
import ch.cpnv.roguetale.save.enums.CommandType;

public class CommandGui extends Gui {

	public CommandGui(Gui prevGui) {
		super(prevGui);
		try {
			this.init();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	public void init() throws SlickException {
		this.buttonList.clear();
		this.labelList.clear();
		int w = Main.BASE_WIDTH,
			h = Main.BASE_HEIGHT;
		
		HashMap<CommandType, Integer> cmd = Main.saveController.getCommand().getCommands();
		int y = 50;
		for (CommandType key : cmd.keySet()) {
			Integer input = cmd.get(key);
			this.labelList.add(new GuiLabel(key.name(), w/4, y, Color.white));
			this.buttonList.add(new ChangeKeyButton(w/2, y, this, key, input));
			y += 50;
		}
		this.buttonList.add(new ReturnButton(w/2, h - 100, this));
		
	}
	
	public void render(GameContainer gc, Graphics g, Vector2f origin) throws SlickException {
		GuiUtils.renderDefaultBackground(g);
		super.render(gc, g, origin);
	}

	public void update(GameContainer gc, int delta, Vector2f origin) throws SlickException {
		super.update(gc, delta, origin);
	}

	public void keyReleased(int key, char c, GameContainer gc) throws SlickException {
		boolean waitingForKey = false;
	
		for (GuiButton gb : this.buttonList) {
			if (gb instanceof ChangeKeyButton) {
				ChangeKeyButton btn = (ChangeKeyButton) gb;
				if (btn.isWaitingForKey()) {
					btn.keyReleased(key, c, gc);
					waitingForKey = true;
				}
			}
		}
		
		if (Input.KEY_ESCAPE == key && !waitingForKey) {
			GuiController.getInstance().setDisplayGui(this.prevGui);
		}

		super.keyReleased(key, c, gc);
	}

	public void keyPressed(int key, char c, GameContainer gc) throws SlickException {
		super.keyPressed(key, c, gc);

	}

	public void mousePressed(int button, int x, int y) throws SlickException {
		boolean waitingForKey = false;
		
		for (GuiButton gb : this.buttonList) {
			if (gb instanceof ChangeKeyButton) {
				ChangeKeyButton btn = (ChangeKeyButton) gb;
				if (btn.isWaitingForKey()) {
					btn.mousePressed(button, x, y);
					waitingForKey = true;
				}
			}
		}
		
		if (!waitingForKey)
			super.mousePressed(button, x, y);

	}
	
	public void mouseMoved(int oldx, int oldy, int newx, int newy) {
		super.mouseMoved(oldx, oldy, newx, newy);
	}

}
