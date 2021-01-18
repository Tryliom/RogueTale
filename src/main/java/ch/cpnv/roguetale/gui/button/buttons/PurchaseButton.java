package ch.cpnv.roguetale.gui.button.buttons;

import java.io.IOException;

import org.newdawn.slick.SlickException;

import ch.cpnv.roguetale.gui.Gui;
import ch.cpnv.roguetale.gui.button.GuiButton;
import ch.cpnv.roguetale.main.Main;
import ch.cpnv.roguetale.save.other.Purchase;

public class PurchaseButton extends GuiButton {
	private Purchase purchase;
	
	public PurchaseButton(int x, int y, Purchase purchase, Gui parentGui) {
		super(x, y, parentGui);
		this.purchase = purchase;
		if (purchase.canBuy())
			this.setContent(purchase.getDescription() + " pour " + purchase.getCost() + " pièces | "+purchase.getLevel()+"/"+purchase.getLevelMax());
		else
			this.setContent(purchase.getDescription() + " | "+purchase.getLevel()+"/"+purchase.getLevelMax());
		this.disabled = !purchase.canBuy();
	}
	

	public void onClick() throws SlickException {
		super.onClick();
		purchase.buy();
		this.parentGui.init();
		try {
			Main.saveController.saveAll();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
