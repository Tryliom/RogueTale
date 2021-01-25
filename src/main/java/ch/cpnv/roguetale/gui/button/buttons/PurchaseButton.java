package ch.cpnv.roguetale.gui.button.buttons;

import java.io.IOException;

import org.newdawn.slick.SlickException;

import ch.cpnv.roguetale.gui.Gui;
import ch.cpnv.roguetale.gui.GuiUtils;
import ch.cpnv.roguetale.gui.button.GuiButton;
import ch.cpnv.roguetale.main.Game;
import ch.cpnv.roguetale.main.Main;
import ch.cpnv.roguetale.purchase.Purchase;

public class PurchaseButton extends GuiButton {
	private Purchase purchase;
	
	public PurchaseButton(int x, int y, Purchase purchase, Gui parentGui) {
		super(x, y, parentGui);
		this.purchase = purchase;
		this.init();
	}
	
	public void init() {
		if (purchase.getLevel() < purchase.getLevelMax())
			this.setContent(purchase.getDisplayName()+ " " + purchase.getLevel()+"/"+purchase.getLevelMax() + " | " + purchase.getCurrentCost() + " pièces");
		else
			this.setContent(purchase.getDisplayName() + " " + purchase.getLevel()+"/"+purchase.getLevelMax());
		this.disabled = !purchase.canBuy();
		purchase.initDescription();
		this.setTooltip(GuiUtils.formatDisplayText(purchase.getDescription(), 300, Game.getInstance().getGc().getGraphics()));
	}

	public void onClick() throws SlickException {
		super.onClick();
		purchase.buy();
		parentGui.init();
		try {
			Main.saveController.saveAll();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
