package ch.cpnv.roguetale.gui.button.buttons;

import ch.cpnv.roguetale.controller.GuiController;
import ch.cpnv.roguetale.gui.Gui;
import ch.cpnv.roguetale.gui.button.GuiButton;
import ch.cpnv.roguetale.gui.guis.GameGui;

public class PlayButton extends GuiButton {

	public PlayButton(String content, int x, int y, int width, int height, Gui parentGui) {
		super(x, y, width, height, parentGui);
		this.content = content;
	}
	
	@Override
	public void onClick() {
		GuiController.getInstance().setDisplayGui(new GameGui(this.parentGui));
	}
}
