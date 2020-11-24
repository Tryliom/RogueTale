package ch.cpnv.roguetale.gui.button.buttons;

import org.newdawn.slick.SlickException;

import ch.cpnv.roguetale.controller.GuiController;
import ch.cpnv.roguetale.gui.Gui;
import ch.cpnv.roguetale.gui.guis.GameGui;
import ch.cpnv.roguetale.weapon.Weapon;

public class ChooseLeftWeaponButton extends ChooseWeaponButton {

	public ChooseLeftWeaponButton(int x, int y, Gui parentGui, Weapon weapon) {
		super(x, y, parentGui, weapon);
		this.content = "Left";
	}
	
	@Override
	public void onClick() throws SlickException {
		super.onClick();
		GameGui.getPlayerController().getPlayer().setPrimaryWeapon(weapon);
		GuiController.getInstance().setDisplayGui(this.parentGui.getPrevGui());
	}

}
