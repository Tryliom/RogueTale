package ch.cpnv.roguetale.gui.guis;

import java.util.ArrayList;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

import ch.cpnv.roguetale.controller.GuiController;
import ch.cpnv.roguetale.entity.character.Player;
import ch.cpnv.roguetale.gui.Gui;
import ch.cpnv.roguetale.gui.GuiUtils;
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
		GuiUtils.renderDefaultBackground(g);
		super.render(gc, g, origin);
		// Display weapons
		Player p = GameGui.getPlayerController().getPlayer();
		Weapon first = p.getPrimaryWeapon();
		Weapon second = p.getSecondaryWeapon();
		
		if (this.desc == null)
			this.desc = GuiUtils.formatDisplayText(first.getDescription(), Main.BASE_HEIGHT/2, g);
		
		if (this.desc2 == null)
			this.desc2 = GuiUtils.formatDisplayText(second.getDescription(), Main.BASE_HEIGHT/2, g);
		
		this.renderWeapons(gc, g, first, 30, this.desc);
		this.renderWeapons(gc, g, second, Main.BASE_WIDTH/2 + 20, this.desc2);
	}
	
	public void renderWeapons(GameContainer gc, Graphics g, Weapon weapon, int x, ArrayList<String> desc) {
		String name = weapon.getName();
		Image icon = weapon.getIcon().getScaledCopy(64, 64);
		Color fill = new Color(63,169,30);
		Color border = new Color(82,193,26);
		
		GuiUtils.renderBox(
				new Rectangle(x - 10, 30, Main.BASE_WIDTH/2 - 30, Main.BASE_HEIGHT*3/4), 
				fill, 
				border, 
				2, g);
		
		int y = 0;
		for (String text : desc) {
			g.drawString(text, x + 100, 100 + y);
			y += 30;
		}
		
		g.drawString(name, x, 40);
		icon.draw(x, 100);
		
		// Caracteristics
		y += 30 + 100;
		if (weapon instanceof RangedWeapon) {
			RangedWeapon w = (RangedWeapon) weapon; 
			g.drawString("Portée: "+w.getRange()+" unitées", x, y); y += 30;
		}
		int damage = weapon.getDamage();
		if (damage != 0) {
			g.drawString("Dégât: "+damage+" coeur" + (damage > 1 ? "s" : ""), x, y); y += 30;
		}
		g.drawString("Recharge: "+(weapon.getCooldown() >= 2000 ? "Long" : "Rapide"), x, y); y += 30;
		
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
