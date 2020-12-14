package ch.cpnv.roguetale.gui.button.buttons;

import org.newdawn.slick.SlickException;

import ch.cpnv.roguetale.controller.GuiController;
import ch.cpnv.roguetale.gui.Gui;
import ch.cpnv.roguetale.gui.guis.GameGui;
import ch.cpnv.roguetale.weapon.Weapon;

public class ChooseRightWeaponButton extends ChooseWeaponButton {
	private boolean canReplace;
	
	public ChooseRightWeaponButton(int x, int y, Gui parentGui, Weapon weapon) {
		super(x, y, parentGui, weapon);
		this.setContent("Remplacer l'arme de droite");
		this.canReplace = true;
		Weapon weap = GameGui.getPlayerController().getPlayer().getSecondaryWeapon();
		
		if (weap.getName().equalsIgnoreCase(weapon.getName())) {
			if (weap.canUpgradeTier()) {
				this.setContent("Augmenter le tier");
			} else {
				this.setContent("Tier maximum");
				this.canReplace = false;
			}
		}
	}
	
	@Override
	public void onClick() throws SlickException {
		super.onClick();
		if (this.canReplace) {
			Weapon weap = GameGui.getPlayerController().getPlayer().getSecondaryWeapon();
			if (weap.getName().equalsIgnoreCase(weapon.getName())) {
				weap.upgradeTier();
				GameGui.getPlayerController().getPlayer().setSecondaryWeapon(weap);
			} else
				GameGui.getPlayerController().getPlayer().setSecondaryWeapon(weapon);
			GuiController.getInstance().setDisplayGui(this.parentGui.getPrevGui());
		}
	}
}
