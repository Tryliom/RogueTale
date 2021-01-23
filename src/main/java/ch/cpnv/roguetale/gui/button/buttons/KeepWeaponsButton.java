package ch.cpnv.roguetale.gui.button.buttons;

import org.newdawn.slick.SlickException;

import ch.cpnv.roguetale.controller.GuiController;
import ch.cpnv.roguetale.controller.MoneyController;
import ch.cpnv.roguetale.gui.Gui;
import ch.cpnv.roguetale.gui.button.GuiButton;
import ch.cpnv.roguetale.main.Main;
import ch.cpnv.roguetale.save.other.Purchase;
import ch.cpnv.roguetale.save.other.purchases.SellWeapon;

public class KeepWeaponsButton extends GuiButton {

	public KeepWeaponsButton(int x, int y, Gui parentGui) {
		super(x, y, parentGui);
		Purchase sellWeapon = Main.saveController.getPurchase().getPurchase(SellWeapon.class);
		this.setContent(sellWeapon.getLevel() == 0 ? "Ignorer" : "Vendre pour "+ (30 * sellWeapon.getLevel()) +" pièces");
	}

	@Override
	public void onClick() throws SlickException {
		super.onClick();
		Purchase sellWeapon = Main.saveController.getPurchase().getPurchase(SellWeapon.class);
		if (sellWeapon.getLevel() >= 1)
			MoneyController.getInstance().addMoney(30 * sellWeapon.getLevel());
		GuiController.getInstance().setDisplayGui(this.parentGui.getPrevGui());
	}
}
