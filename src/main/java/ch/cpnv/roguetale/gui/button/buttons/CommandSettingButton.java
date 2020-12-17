package ch.cpnv.roguetale.gui.button.buttons;

import org.newdawn.slick.SlickException;

import ch.cpnv.roguetale.controller.GuiController;
import ch.cpnv.roguetale.gui.Gui;
import ch.cpnv.roguetale.gui.button.GuiButton;
import ch.cpnv.roguetale.gui.guis.CommandGui;

public class CommandSettingButton extends GuiButton {

	public CommandSettingButton(int x, int y, Gui parentGui) {
		super(x, y, parentGui);
		this.content = "Commande";
	}
	
	@Override
	public void onClick() throws SlickException {
		super.onClick();
		GuiController.getInstance().setDisplayGui(new CommandGui(this.parentGui));
	}
}
