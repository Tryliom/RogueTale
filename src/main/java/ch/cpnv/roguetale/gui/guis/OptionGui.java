package ch.cpnv.roguetale.gui.guis;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import ch.cpnv.roguetale.controller.GuiController;
import ch.cpnv.roguetale.gui.Gui;
import ch.cpnv.roguetale.gui.button.buttons.GraphicSettingButton;
import ch.cpnv.roguetale.gui.button.buttons.ReturnButton;
import ch.cpnv.roguetale.gui.button.buttons.SoundSettingButton;
import ch.cpnv.roguetale.main.Main;

public class OptionGui extends Gui {
	private static final String PATH_PANEL = "ch\\cpnv\\roguetale\\images\\ui\\panel\\panel_blue.png";
	private Image background;

	public OptionGui(Gui prevGui) {
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
		this.background = new Image(PATH_PANEL);
		this.background.setFilter(Image.FILTER_NEAREST);
		int w = Main.BASE_WIDTH,
			h = Main.BASE_HEIGHT;
		
		this.buttonList.add(new SoundSettingButton(w/2, h/4, this));
		this.buttonList.add(new GraphicSettingButton(w/2, h*3/8, this));
		this.buttonList.add(new ReturnButton(w/2, h*3/4, this));
		
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
