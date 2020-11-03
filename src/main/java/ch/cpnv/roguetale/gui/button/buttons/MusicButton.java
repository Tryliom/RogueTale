package ch.cpnv.roguetale.gui.button.buttons;

import java.io.IOException;
import java.util.ArrayList;

import org.newdawn.slick.SlickException;

import ch.cpnv.roguetale.gui.Gui;
import ch.cpnv.roguetale.gui.button.GuiSwitchButton;
import ch.cpnv.roguetale.save.SaveManager;

public class MusicButton extends GuiSwitchButton {

	public MusicButton(int x, int y, Gui parentGui) {
		super(x, y, parentGui);
		this.init();
	}
	
	private void init() {
		ArrayList<Integer> data = new ArrayList<Integer>();
		for (int i=0;i<=10;i++)
			data.add(i);
		
		this.selected = -1;
		this.data = data;
		this.content = data.get(this.selected) * 10 + "%";
	}
	
	@Override
	public void onClick() throws SlickException {
		super.onClick();
		Integer volume = (Integer) this.data.get(this.selected);
		
		
		this.content = volume * 10 + "%";

		try {
			new SaveManager().saveGraphic();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

}
