package ch.cpnv.roguetale.gui;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class GuiLabel {
	protected String content;
	protected int x;
	protected int y;
	protected Color color;
	
	public GuiLabel(String content, int x, int y, Color color) {
		this.content = content;
		this.x = x;
		this.y = y;
		this.color = color;
	}

	public void render(GameContainer gc, Graphics g) {
		// Default display of a label
		Color old = g.getColor();
		g.setColor(color);
		g.drawString(content, x - g.getFont().getWidth(content)/2, y);
		g.setColor(old);
	}

	

}
