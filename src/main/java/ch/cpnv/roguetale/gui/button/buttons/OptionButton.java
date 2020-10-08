package ch.cpnv.roguetale.gui.button.buttons;

import ch.cpnv.roguetale.controller.GuiController;
import ch.cpnv.roguetale.gui.Gui;
import ch.cpnv.roguetale.gui.button.GuiButton;
import ch.cpnv.roguetale.gui.guis.OptionGui;

public class OptionButton extends GuiButton {

	public OptionButton(int x, int y, int width, int height, Gui parentGui) {
		super(x, y, width, height, parentGui);
		this.content = "Options...";
	}
	
	@Override
	public void onClick() {
		GuiController.getInstance().setDisplayGui(new OptionGui(this.parentGui));
	}
}
