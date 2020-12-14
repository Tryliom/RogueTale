package ch.cpnv.roguetale.gui.guis;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import ch.cpnv.roguetale.controller.GuiController;
import ch.cpnv.roguetale.gui.Gui;
import ch.cpnv.roguetale.gui.GuiUtils;
import ch.cpnv.roguetale.gui.button.buttons.ChooseLeftWeaponButton;
import ch.cpnv.roguetale.gui.button.buttons.ChooseRightWeaponButton;
import ch.cpnv.roguetale.gui.button.buttons.KeepWeaponsButton;
import ch.cpnv.roguetale.main.Main;
import ch.cpnv.roguetale.weapon.Weapon;

public class ChangeWeaponGui extends Gui {
	private Weapon weapon;
	
	public ChangeWeaponGui(Gui prevGui, Weapon weapon) {
		super(prevGui);
		this.weapon = weapon;
		
		int width = Main.BASE_WIDTH,
			height = Main.BASE_HEIGHT;
		
		this.buttonList.add(new ChooseLeftWeaponButton(width/4, height*3/4, this, weapon));
		this.buttonList.add(new KeepWeaponsButton(width/2, height*3/4, this));
		this.buttonList.add(new ChooseRightWeaponButton(3*width/4 , height*3/4, this, weapon));
	}
	
	public void render(GameContainer gc, Graphics g, Vector2f origin) throws SlickException {
		int width = Main.BASE_WIDTH,
			height = Main.BASE_HEIGHT;
		
		GuiUtils.renderDefaultBackground(g);
		weapon.getIcon().drawCentered(width/2, height/2);
		g.drawString(weapon.getName(), width/2 - g.getFont().getWidth(weapon.getName())/2, height/4);
		super.render(gc, g, origin);
	}
	
	public void keyReleased(int key, char c, GameContainer gc) throws SlickException {
		if (Input.KEY_ESCAPE == key) {
			GuiController.getInstance().setDisplayGui(this.prevGui);
		}

		super.keyReleased(key, c, gc);
	}

}
