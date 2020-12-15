package ch.cpnv.roguetale.gui.button.buttons;

import java.io.IOException;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import ch.cpnv.roguetale.gui.Gui;
import ch.cpnv.roguetale.gui.button.GuiButton;
import ch.cpnv.roguetale.gui.guis.GameGui;
import ch.cpnv.roguetale.main.Main;
import ch.cpnv.roguetale.save.enums.CommandType;

public class ChangeKeyButton extends GuiButton {
	private CommandType cmd;
	private Integer key;
	private boolean waitingForKey;
	
	public ChangeKeyButton(int x, int y, Gui parentGui, CommandType cmd, Integer key) {
		super(x, y, parentGui);
		this.cmd = cmd;
		this.key = key;
		setContentWithKey();
	}
	
	@Override
	public void onClick() throws SlickException {
		super.onClick();
		this.setContent("Appuyer sur une touche");
		this.waitingForKey = true;
	}
	
	public void keyReleased(int key, char c, GameContainer gc) throws SlickException {
		this.onKey(key, false);
	}
	
	public void mousePressed(int button, int x, int y) throws SlickException {
		if (this.waitingForKey) {
			this.waitingForKey = false;
			setContentWithKey();
		}
	}
	
	public void onKey(int key, boolean mouse) {
		if (this.waitingForKey) {
			this.waitingForKey = false;
			if (Input.KEY_ESCAPE == key && !mouse) {
				setContentWithKey();
			} else {
				Main.saveController.getCommand().setKey(cmd, key);
				try {
					Main.saveController.saveAll();
					if (GameGui.getPlayerController() != null)
						GameGui.getPlayerController().setupKeys();
				} catch (IOException e) {
					e.printStackTrace();
				}
				this.key = key;
				setContentWithKey();
			}
		}
	}

	public boolean isWaitingForKey() {
		return waitingForKey;
	}
	
	public void setContentWithKey() {
		this.setContent(Input.getKeyName(this.key));
	}
}
