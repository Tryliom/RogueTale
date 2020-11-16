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

public class ResolutionButton extends GuiSwitchButton {

	public ResolutionButton(int x, int y, Gui parentGui) {
		super(x, y, parentGui);
		this.init();
	}
	
	private void init() {
		int w = Main.BASE_WIDTH,
			h = Main.BASE_HEIGHT;
		GameContainer gc = Game.getInstance().getGc();
		Vector2f resolution = new Vector2f(gc.getWidth(), gc.getHeight());
		int x = Math.round(resolution.x),
			y = Math.round(resolution.y);
		
		ArrayList<Vector2f> data = new ArrayList<Vector2f>();
		data.add(new Vector2f(w, h));
		data.add(new Vector2f(w*2, h*2));
		
		this.selected = data.indexOf(resolution);
		this.data = data;
		this.content = x + "x" + y;
	}
	
	@Override
	public void onClick() throws SlickException {
		super.onClick();
		Vector2f res = (Vector2f) this.data.get(this.selected);
		Main.saveController.getGraphic().setResolution(res);
		int x = Math.round(res.x),
			y = Math.round(res.y);
		
		this.content = x + "x" + y;
		Main.app.setDisplayMode(x, y, Main.app.isFullscreen());
		try {
			new SaveManager().saveGraphic();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

}
