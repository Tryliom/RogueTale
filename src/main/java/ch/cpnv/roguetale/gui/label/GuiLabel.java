package ch.cpnv.roguetale.gui.label;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class GuiLabel {
	protected String content;
	protected int x;
	protected int y;
	
	public GuiLabel(String content, int x, int y) {
		this.content = content;
		this.x = x;
		this.y = y;
	}

	public void render(GameContainer gc, Graphics g) {
		// Default display of a label
	}

	

}
