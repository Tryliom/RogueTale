package ch.cpnv.roguetale.controller;

import java.util.HashMap;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import ch.cpnv.roguetale.gui.Gui;
import ch.cpnv.roguetale.gui.guis.GameGui;
import ch.cpnv.roguetale.gui.guis.MenuGui;
import ch.cpnv.roguetale.sound.SoundManager;
import ch.cpnv.roguetale.sound.SoundType;

public class GuiController implements Controller {
	private Gui currentGui;
	private static GuiController instance;
	
	// Note : int is not a possible key for HashMap
	HashMap<Integer, Gui> guiWhenKeypressed = new HashMap<Integer, Gui>();
	
	public static GuiController getInstance() {
		return instance == null ? instance = new GuiController() : instance;
	}
	
	private GuiController() {}
	
	public void init() {
		this.currentGui = new MenuGui(null);
	}
	
	@Override
	public void render(GameContainer gc, Graphics g, Vector2f origin) throws SlickException {
		this.currentGui.render(gc, g, origin);
	}

	@Override
	public void update(GameContainer gc, int delta, Vector2f origin) throws SlickException {
		this.currentGui.update(gc, delta, origin);
	}

	@Override
	public void keyReleased(int key, char c, GameContainer gc) throws SlickException {
		if(pressedInCurrentGui(key)) {
			this.currentGui.keyReleased(key, c, gc);
		}	
	}

	@Override
	public void keyPressed(int key, char c, GameContainer gc) throws SlickException {
		setPressedInCurrentGui(key);
		this.currentGui.keyPressed(key, c, gc);

	}

	@Override
	public void mousePressed(int button, int x, int y) throws SlickException {
		setPressedInCurrentGui(button);
		this.currentGui.mousePressed(button, x, y);
	}
	
	@Override 
	public void mouseMoved(int oldx, int oldy, int newx, int newy) {
		this.currentGui.mouseMoved(oldx, oldy, newx, newy);
	}
	
	public void setDisplayGui(Gui gui) {
		this.currentGui = gui;
		if (gui instanceof GameGui) {
			try {
				SoundManager.getInstance().stop(SoundType.MainTheme);
				SoundManager.getInstance().loop(SoundType.MainTheme);
			} catch (SlickException e) {
				e.printStackTrace();
			}
		}
		if (GameGui.getPlayerController() != null)
			GameGui.getPlayerController().getPlayer().setMoving(false);
	}
	
	public Gui getDisplayGui() {
		return currentGui;
	}

	@Override
	public void mouseReleased(int button, int x, int y) throws SlickException {
		if(pressedInCurrentGui(button)) {
			this.currentGui.mouseReleased(button, x, y);
		}
	}
	
	protected boolean pressedInCurrentGui(int key) {
		return currentGui == guiWhenKeypressed.get(Integer.valueOf(key));
	}
	
	protected void setPressedInCurrentGui(int key) {
		guiWhenKeypressed.put(Integer.valueOf(key), currentGui);
	}

}
