package ch.cpnv.roguetale.gui.button.buttons;

import org.newdawn.slick.SlickException;

import ch.cpnv.roguetale.controller.GuiController;
import ch.cpnv.roguetale.gui.Gui;
import ch.cpnv.roguetale.gui.button.GuiButton;

public class ReturnButton extends GuiButton {

	public ReturnButton(int x, int y, Gui parentGui) {
		super(x, y, parentGui);
		this.content = "Retour";
	}
	

	public void onClick() throws SlickException {
		super.onClick();
		GuiController.getInstance().setDisplayGui(this.parentGui.getPrevGui());
	}
}
