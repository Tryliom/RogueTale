package ch.cpnv.roguetale.gui.button.buttons;

import org.newdawn.slick.SlickException;

import ch.cpnv.roguetale.controller.GuiController;
import ch.cpnv.roguetale.gui.Gui;
import ch.cpnv.roguetale.gui.button.GuiButton;
import ch.cpnv.roguetale.gui.guis.MenuGui;

public class ReturnToMenuButton extends GuiButton {

	public ReturnToMenuButton(int x, int y, Gui parentGui) {
		super(x, y, parentGui);
		this.content = "Retour au menu";
	}
	

	public void onClick() throws SlickException {
		super.onClick();
		GuiController.getInstance().setDisplayGui(new MenuGui(this.parentGui));
	}
}
