package ch.cpnv.roguetale.gui.guis;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import ch.cpnv.roguetale.gui.Gui;
import ch.cpnv.roguetale.main.Main;

public class ChangeWeaponGui extends Gui {
	private static final String PATH_PANEL = "ch\\cpnv\\roguetale\\images\\ui\\panel\\panel_blue.png";
	private Image background;
	
	public ChangeWeaponGui(Gui prevGui) {
		super(prevGui);
		try {
			this.background = new Image(PATH_PANEL);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		this.background.setFilter(Image.FILTER_NEAREST);
	}
	
	public void render(GameContainer gc, Graphics g, Vector2f origin) throws SlickException {
		this.prevGui.render(gc, g, origin);
		this.background.draw(Main.BASE_WIDTH/10, Main.BASE_HEIGHT/10, Main.BASE_WIDTH * 0.8f, Main.BASE_HEIGHT * 0.8f);
		super.render(gc, g, origin);
	}

}
