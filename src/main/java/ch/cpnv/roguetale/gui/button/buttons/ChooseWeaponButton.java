package ch.cpnv.roguetale.gui.button.buttons;

import ch.cpnv.roguetale.gui.Gui;
import ch.cpnv.roguetale.gui.button.GuiButton;
import ch.cpnv.roguetale.weapon.Weapon;

public abstract class ChooseWeaponButton extends GuiButton {
	protected Weapon weapon;
	
	public ChooseWeaponButton(int x, int y, Gui parentGui, Weapon weapon) {
		super(x, y, parentGui);
		this.weapon = weapon;
	}

}
