package ch.cpnv.roguetale.gui.button.buttons;

import org.newdawn.slick.SlickException;

import ch.cpnv.roguetale.controller.GuiController;
import ch.cpnv.roguetale.gui.Gui;
import ch.cpnv.roguetale.gui.button.GuiButton;
import ch.cpnv.roguetale.gui.guis.SoundGui;

public class SoundButton extends GuiButton {

	public SoundButton(int x, int y, Gui parentGui) {
		super(x, y, parentGui);
		this.content = "Son";
	}
	
	@Override
	public void onClick() throws SlickException {
		super.onClick();
		GuiController.getInstance().setDisplayGui(new SoundGui(this.parentGui));
	}
}
