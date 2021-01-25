package ch.cpnv.roguetale.entity.ui;


import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

import ch.cpnv.roguetale.gui.GuiUtils;

public class UiLifePoint {
	private static final int WIDTH_BAR_BASE = 300;
	private static final int WIDTH_BAR_MAX = 700;
	private int base_life;
	private int current_life;
	private int current_max_life;
	
	public UiLifePoint(int base_life) {
		this.base_life = base_life;
		this.current_life = base_life;
		this.current_max_life = base_life;
	}
	
	public void render(Graphics g) {
		int width = WIDTH_BAR_BASE * current_max_life / base_life;
		if (width > WIDTH_BAR_MAX)
			width = WIDTH_BAR_MAX;
		Rectangle progressBar = new Rectangle(5, 5, width, 30);
		GuiUtils.renderBar(progressBar, Color.darkGray, Color.darkGray, new Color(200, 70, 70), 2, (float) current_life / current_max_life, g);
		String life = this.current_life + "/" + this.current_max_life;
		g.drawString(life, width - g.getFont().getWidth(life), g.getFont().getHeight(life)/3);
	}

	public int getCurrent_life() {
		return current_life;
	}

	public void setCurrent_life(int current_life) {
		this.current_life = current_life;
	}

	public int getCurrent_max_life() {
		return current_max_life;
	}

	public void setCurrent_max_life(int current_max_life) {
		this.current_max_life = current_max_life;
	}

	
}
