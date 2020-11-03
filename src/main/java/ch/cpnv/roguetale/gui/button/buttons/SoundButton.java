package ch.cpnv.roguetale.gui.button.buttons;

import java.io.IOException;
import java.util.ArrayList;

import org.newdawn.slick.SlickException;

import ch.cpnv.roguetale.gui.Gui;
import ch.cpnv.roguetale.gui.button.GuiSwitchButton;
import ch.cpnv.roguetale.main.Main;

public class SoundButton extends GuiSwitchButton {

	public SoundButton(int x, int y, Gui parentGui) {
		super(x, y, parentGui);
		this.init();
	}
	
	private void init() {
		ArrayList<Integer> data = new ArrayList<Integer>();
		for (int i=0;i<=10;i++)
			data.add(i);
		
		this.selected = data.indexOf(Main.saveController.getSound().getSound());
		this.data = data;
		this.content = "Bruitage à " + data.get(this.selected) * 10 + "%";
	}
	
	@Override
	public void onClick() throws SlickException {
		super.onClick();
		Integer volume = (Integer) this.data.get(this.selected);
		
		this.content = "Bruitage à " + volume * 10 + "%";

		Main.saveController.getSound().setSound(volume);
		
		try {
			Main.saveController.saveAll();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
