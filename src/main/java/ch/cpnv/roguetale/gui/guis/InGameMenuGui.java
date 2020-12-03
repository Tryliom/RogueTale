package ch.cpnv.roguetale.gui.guis;

import java.util.ArrayList;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import ch.cpnv.roguetale.controller.GuiController;
import ch.cpnv.roguetale.entity.character.Player;
import ch.cpnv.roguetale.gui.Gui;
import ch.cpnv.roguetale.gui.button.buttons.OptionButton;
import ch.cpnv.roguetale.gui.button.buttons.ReturnButton;
import ch.cpnv.roguetale.main.Main;
import ch.cpnv.roguetale.weapon.MeleeWeapon;
import ch.cpnv.roguetale.weapon.RangedWeapon;
import ch.cpnv.roguetale.weapon.Weapon;

public class InGameMenuGui extends Gui {
	private ArrayList<String> desc;
	private ArrayList<String> desc2;
	
	public InGameMenuGui(Gui prevGui) {
		super(prevGui);
		try {
			this.init();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	public void init() throws SlickException {
		int w = Main.BASE_WIDTH,
			h = Main.BASE_HEIGHT;

		this.buttonList.add(new OptionButton(w*3/4, h - 80, this));		
		this.buttonList.add(new ReturnButton(w/4, h - 80, this));
	}
	
	public void render(GameContainer gc, Graphics g, Vector2f origin) throws SlickException {
		this.renderDefaultBackground(g);
		super.render(gc, g, origin);
		// Display weapons
		Player p = GameGui.getPlayerController().getPlayer();
		Weapon first = p.getPrimaryWeapon();
		Weapon second = p.getSecondaryWeapon();
		
		if (this.desc == null)
			this.desc = this.formatDescription(first.getDescription(), Main.BASE_HEIGHT/4, g);
		
		if (this.desc2 == null)
			this.desc2 = this.formatDescription(second.getDescription(), Main.BASE_HEIGHT/4, g);
		
		this.renderWeapons(gc, g, first, 30, this.desc);
		this.renderWeapons(gc, g, second, Main.BASE_WIDTH/2, this.desc2);
	}
	
	public ArrayList<String> formatDescription(String desc, int maxSpace, Graphics g) {
		ArrayList<String> list = new ArrayList<String>();
		String description = "";
		for (int i = 0;i<desc.length();i++) {
			if (description.length() * g.getFont().getWidth(" ") > maxSpace - 30 && Character.isWhitespace(desc.charAt(i))) {
				list.add(description);
				description = "";
			} else			
				description += desc.charAt(i);
		}
		if (description != "")
			list.add(description);
		
		return list;
	}
	
	public void renderWeapons(GameContainer gc, Graphics g, Weapon weapon, int x, ArrayList<String> desc) {
		String name = weapon.getName();
		Image icon = weapon.getIcon().getScaledCopy(64, 64);
		
		int y = 0;
		for (String text : desc) {
			g.drawString(text, x + 100, 100 + y);
			y += 30;
		}
		
		g.drawString(name, x, 40);
		icon.draw(x, 100);
		
		// Caracteristics
		
		if (weapon instanceof RangedWeapon) {
			RangedWeapon w = (RangedWeapon) weapon;
			
		} else if (weapon instanceof MeleeWeapon) {
			MeleeWeapon w = (MeleeWeapon) weapon;
			
		} else {
			
		}
		
	}

	public void update(GameContainer gc, int delta, Vector2f origin) throws SlickException {
		super.update(gc, delta, origin);

	}

	public void keyReleased(int key, char c, GameContainer gc) throws SlickException {
		if (Input.KEY_ESCAPE == key) {
			GuiController.getInstance().setDisplayGui(this.prevGui);
		}

		super.keyReleased(key, c, gc);
	}

	public void keyPressed(int key, char c, GameContainer gc) throws SlickException {
		super.keyPressed(key, c, gc);

	}

	public void mousePressed(int button, int x, int y) throws SlickException {
		super.mousePressed(button, x, y);

	}
	
	public void mouseMoved(int oldx, int oldy, int newx, int newy) {
		super.mouseMoved(oldx, oldy, newx, newy);
	}

}
