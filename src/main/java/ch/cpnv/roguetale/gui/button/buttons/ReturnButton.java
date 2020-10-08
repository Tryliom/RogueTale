package ch.cpnv.roguetale.gui.button.buttons;

import ch.cpnv.roguetale.controller.GuiController;
import ch.cpnv.roguetale.gui.Gui;
import ch.cpnv.roguetale.gui.button.GuiButton;

public class ReturnButton extends GuiButton {

	public ReturnButton(int x, int y, int width, int height, Gui parentGui) {
		super(x, y, width, height, parentGui);
		this.content = "Retour";
	}
	
	@Override
	public void onClick() {
		GuiController.getInstance().setDisplayGui(this.parentGui.getPrevGui());
	}
}
