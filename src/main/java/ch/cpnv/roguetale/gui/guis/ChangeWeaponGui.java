package ch.cpnv.roguetale.gui.guis;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import ch.cpnv.roguetale.gui.Gui;
import ch.cpnv.roguetale.gui.button.buttons.ChooseLeftWeaponButton;
import ch.cpnv.roguetale.gui.button.buttons.ChooseRightWeaponButton;
import ch.cpnv.roguetale.gui.button.buttons.KeepWeaponsButton;
import ch.cpnv.roguetale.main.Main;
import ch.cpnv.roguetale.weapon.Weapon;

public class ChangeWeaponGui extends Gui {
	private static final String PATH_PANEL = "ch\\cpnv\\roguetale\\images\\ui\\panel\\panel_blue.png";
	private Image background;
	private Weapon weapon;
	
	public ChangeWeaponGui(Gui prevGui, Weapon weapon) {
		super(prevGui);
		this.weapon = weapon;
		
		int width = Main.BASE_WIDTH,
			height = Main.BASE_HEIGHT;
		try {
			this.background = new Image(PATH_PANEL);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		this.buttonList.add(new ChooseLeftWeaponButton(width/4, height*3/4, this, weapon));
		this.buttonList.add(new KeepWeaponsButton(width/2, height*3/4, this));
		this.buttonList.add(new ChooseRightWeaponButton(3*width/4, height*3/4, this, weapon));
		this.background.setFilter(Image.FILTER_NEAREST);
	}
	
	public void render(GameContainer gc, Graphics g, Vector2f origin) throws SlickException {
		int width = Main.BASE_WIDTH,
			height = Main.BASE_HEIGHT;
		
		this.prevGui.render(gc, g, origin);
		this.background.draw(Main.BASE_WIDTH/10, Main.BASE_HEIGHT/10, Main.BASE_WIDTH * 0.8f, Main.BASE_HEIGHT * 0.8f);
		weapon.getIcon().drawCentered(width/2, height/2);
		g.drawString(weapon.getName(), width/2 - g.getFont().getWidth(weapon.getName())/2, height/4);
		super.render(gc, g, origin);
	}

}
