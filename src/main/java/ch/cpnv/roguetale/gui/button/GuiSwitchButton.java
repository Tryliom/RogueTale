package ch.cpnv.roguetale.gui.button;

import java.util.ArrayList;

import org.newdawn.slick.SlickException;

import ch.cpnv.roguetale.gui.Gui;

public class GuiSwitchButton extends GuiButton {
	protected ArrayList<?> data;
	protected int selected;
	
	public GuiSwitchButton(int x, int y, Gui parentGui) {
		super(x, y, parentGui);
	}
	
	public void onClick() throws SlickException {
		super.onClick();
		selected++;
		if (selected == data.size()) {
			selected = 0;
		}
	}
	
	
}
