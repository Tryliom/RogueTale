package ch.cpnv.roguetale.gui.button.buttons;

import java.io.IOException;
import java.util.ArrayList;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

import ch.cpnv.roguetale.gui.Gui;
import ch.cpnv.roguetale.gui.button.GuiSwitchButton;
import ch.cpnv.roguetale.main.Main;
import ch.cpnv.roguetale.model.Game;
import ch.cpnv.roguetale.save.SaveManager;

public class FullscreenButton extends GuiSwitchButton {

	public FullscreenButton(int x, int y, int width, int height, Gui parentGui) {
		super(x, y, width, height, parentGui);
		this.init();
	}
	
	private void init() {
		GameContainer gc = Game.getInstance().getGc();
		ArrayList<Boolean> data = new ArrayList<Boolean>();
		data.add(true); data.add(false);
		
		this.selected = gc.isFullscreen() ? 0 : 1;
		this.data = data;
		this.content = "Plein écran: "+(gc.isFullscreen() ? "Oui" : "Non");
	}
	
	@Override
	public void onClick() throws SlickException {
		super.onClick();
		int w = Main.BASE_WIDTH,
			h = Main.BASE_HEIGHT;
		Vector2f fallbackForFullscreen = new Vector2f(w*2, h*2);
		Boolean fullscreen = (Boolean) this.data.get(this.selected);
		
		try {
			Main.data.setFullscreen(fullscreen);
			Main.app.setFullscreen(fullscreen);
		} catch(Exception e) {
			Main.app.setDisplayMode(Math.round(fallbackForFullscreen.x), Math.round(fallbackForFullscreen.y), fullscreen);
			Main.data.setResolution(fallbackForFullscreen);
		}
		
		try {
			new SaveManager().saveSettings();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		this.content = "Plein écran: "+(fullscreen ? "Oui" : "Non");
		this.parentGui.init();
	}
}
