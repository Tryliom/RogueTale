package ch.cpnv.roguetale.gui.button;

import java.util.ArrayList;

import org.newdawn.slick.SlickException;

import ch.cpnv.roguetale.gui.Gui;

public class GuiSwitchButton extends GuiButton {
	protected ArrayList<?> data;
	protected int selected;
	
	public GuiSwitchButton(int x, int y, int width, int height, Gui parentGui) {
		super(x, y, width, height, parentGui);
	}
	
	@Override
	public void onClick() throws SlickException {
		selected++;
		if (selected == data.size()) {
			selected = 0;
		}
	}
	
	
}
