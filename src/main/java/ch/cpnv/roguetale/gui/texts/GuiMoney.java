package ch.cpnv.roguetale.gui.texts;

import org.newdawn.slick.Color;

import ch.cpnv.roguetale.controller.MoneyController;

public class GuiMoney extends GuiLabel {

	public GuiMoney(int x, int y) {
		super("", x, y, Color.yellow);
	}
	
	public void update() {
		content = Integer.toString(MoneyController.getInstance().getMoney());
	}

}
