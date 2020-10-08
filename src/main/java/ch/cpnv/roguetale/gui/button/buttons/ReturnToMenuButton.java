package ch.cpnv.roguetale.gui.button.buttons;

import ch.cpnv.roguetale.controller.GuiController;
import ch.cpnv.roguetale.gui.Gui;
import ch.cpnv.roguetale.gui.button.GuiButton;
import ch.cpnv.roguetale.gui.guis.MenuGui;

public class ReturnToMenuButton extends GuiButton {

	public ReturnToMenuButton(int x, int y, int width, int height, Gui parentGui) {
		super(x, y, width, height, parentGui);
		this.content = "Retour au menu";
	}
	
	@Override
	public void onClick() {
		GuiController.getInstance().setDisplayGui(new MenuGui(this.parentGui));
	}
}
