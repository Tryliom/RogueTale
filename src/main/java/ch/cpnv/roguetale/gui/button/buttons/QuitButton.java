package ch.cpnv.roguetale.gui.button.buttons;

import org.newdawn.slick.SlickException;

import ch.cpnv.roguetale.gui.Gui;
import ch.cpnv.roguetale.gui.button.GuiButton;
import ch.cpnv.roguetale.model.Game;

public class QuitButton extends GuiButton {

	public QuitButton(int x, int y, Gui parentGui) {
		super(x, y, parentGui);
		this.content = "Quitter";
	}
	
	@Override
	public void onClick() throws SlickException {
		super.onClick();
		Game.getInstance().getGc().exit();
	}
}
