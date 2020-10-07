package ch.cpnv.roguetale.gui.guis;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import ch.cpnv.roguetale.gui.ExecuteWithArgs;
import ch.cpnv.roguetale.gui.GuiButton;
import ch.cpnv.roguetale.gui.Transforming;

public class GuiSwitchButton extends GuiButton {
	private Transforming funcTransform;
	private ExecuteWithArgs func;
	private ArrayList<?> data;
	private int selected;
	
	public GuiSwitchButton(int x, int y, int width, int height, ExecuteWithArgs func, Transforming transform, ArrayList<?> data, int selected) {
		super(transform.transform(data.get(selected)), x, y, width, height, null);
		this.funcTransform = transform;
		this.data = data;
		this.selected = selected;
		this.func = func;
	}
	
	@Override
	public void onClick() throws SlickException {
		selected++;
		if (selected == data.size()) {
			selected = 0;
		}
		this.content = funcTransform.transform(data.get(selected));
		this.func.execute(data.get(selected));
	}
	
	public void render(GameContainer gc, Graphics g) throws SlickException {
		super.render(gc, g);
	}
	
	public void mouseHover(int oldx, int oldy, int newx, int newy) {
		super.mouseHover(oldx, oldy, newx, newy);
	}

}
