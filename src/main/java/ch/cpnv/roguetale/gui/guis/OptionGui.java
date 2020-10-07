package ch.cpnv.roguetale.gui.guis;

import java.util.ArrayList;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import ch.cpnv.roguetale.controller.GuiController;
import ch.cpnv.roguetale.gui.Execute;
import ch.cpnv.roguetale.gui.ExecuteWithArgs;
import ch.cpnv.roguetale.gui.Gui;
import ch.cpnv.roguetale.gui.GuiButton;
import ch.cpnv.roguetale.gui.Transforming;
import ch.cpnv.roguetale.main.Main;
import ch.cpnv.roguetale.model.Game;

public class OptionGui extends Gui {
	private static final String PATH_PANEL = "ch\\cpnv\\roguetale\\images\\ui\\panel\\panel_blue.png";
	private Image background;
	private Vector2f resolution;

	public OptionGui(Gui prevGui) {
		super(prevGui);
		try {
			this.init();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	private void init() throws SlickException {
		GameContainer gc = Game.getInstance().getGc();
		int w = Main.BASE_WIDTH,
			h = Main.BASE_HEIGHT;
		this.background = new Image(PATH_PANEL);
		this.background.setFilter(Image.FILTER_NEAREST);
		this.resolution = new Vector2f(gc.getWidth(), gc.getHeight());
		
		ArrayList<Vector2f> listResolution = new ArrayList<Vector2f>();
		listResolution.add(new Vector2f(w, h));
		listResolution.add(new Vector2f(w*3/2, h*3/2));
		listResolution.add(new Vector2f(w*2, h*2));
		listResolution.add(new Vector2f(w*3, h*3));
		int selected = 0;
		
		for (int i = 0;i<listResolution.size();i++) {
			Vector2f obj = listResolution.get(i);
			if (obj.x == this.resolution.x) {
				selected = i;
				break;
			}
		}
		
		ExecuteWithArgs changeResolution = (Object obj) -> {
			Vector2f res = (Vector2f) obj;
			Main.app.setDisplayMode(Math.round(res.x), Math.round(res.y), gc.isFullscreen());
		};
		Transforming transform = (Object obj) -> {
			Vector2f res = (Vector2f) obj;
			return Math.round(res.x) + "x" + Math.round(res.y);
		};
		
		this.buttonList.add(new GuiSwitchButton(w/2 - 100, h/4, 200, 40, changeResolution, transform, listResolution, selected));
		
		ArrayList<Boolean> listFs = new ArrayList<Boolean>();
		listFs.add(true); listFs.add(false);
		
		ExecuteWithArgs changeFs = (Object obj) -> {
			Boolean fs = (Boolean) obj;
			gc.setFullscreen(fs);
		};
		Transforming transformFs = (Object obj) -> {
			Boolean fs = (Boolean) obj;
			return "Plein écran: "+(fs ? "Oui" : "Non");
		};
		
		this.buttonList.add(new GuiSwitchButton(w/2 - 100, h*3/8, 200, 40, changeFs, transformFs, listFs, gc.isFullscreen() ? 0 : 1));
		
		Execute funcReturn = () -> {GuiController.getInstance().setDisplayGui(this.prevGui);};
		
		this.buttonList.add(new GuiButton("Retour", w/2 - 100, h*3/4, 200, 40, funcReturn));
		
	}
	
	public void render(GameContainer gc, Graphics g, Vector2f origin) throws SlickException {
		this.prevGui.render(gc, g, origin);
		this.background.draw(Main.BASE_WIDTH/10, Main.BASE_HEIGHT/10, Main.BASE_WIDTH - Main.BASE_WIDTH/5, Main.BASE_HEIGHT - Main.BASE_HEIGHT/5);
		super.render(gc, g, origin);
	}

	public void update(GameContainer gc, int delta, Vector2f origin) throws SlickException {
		super.update(gc, delta, origin);

	}

	public void keyReleased(int key, char c, GameContainer gc) throws SlickException {
		if (Input.KEY_ESCAPE == key) {
			GuiController.getInstance().setDisplayGui(this.prevGui);
		}

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
