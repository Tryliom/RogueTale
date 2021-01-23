package ch.cpnv.roguetale.gui.guis;

import java.util.ArrayList;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import ch.cpnv.roguetale.controller.GuiController;
import ch.cpnv.roguetale.entity.ui.UiMoney;
import ch.cpnv.roguetale.gui.Gui;
import ch.cpnv.roguetale.gui.GuiUtils;
import ch.cpnv.roguetale.gui.button.GuiButton;
import ch.cpnv.roguetale.gui.button.buttons.PurchaseButton;
import ch.cpnv.roguetale.gui.button.buttons.ReturnButton;
import ch.cpnv.roguetale.gui.list.GuiScrollableList;
import ch.cpnv.roguetale.gui.list.Slot;
import ch.cpnv.roguetale.main.Main;
import ch.cpnv.roguetale.save.other.Purchase;

public class ShopGui extends Gui {
	private UiMoney moneyDisplayer;

	public ShopGui(Gui prevGui) {
		super(prevGui);
		try {
			this.init();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	public void init() throws SlickException {
		this.buttonList.clear();
		this.labelList.clear();
		this.scollList.clear();
		int w = Main.BASE_WIDTH,
			h = Main.BASE_HEIGHT;
		
		this.moneyDisplayer = new UiMoney(w/2, 50);
		ArrayList<Purchase> purchases = Main.saveController.getPurchase().getPurchases();
		ArrayList<Slot> list = new ArrayList<Slot>();
		
		for (Purchase p : purchases) {
			ArrayList<GuiButton> btn = new ArrayList<GuiButton>();
			btn.add(new PurchaseButton((w - 50)/2, 0, p, this));
			list.add(new Slot(btn, null));
		}
		
		this.scollList.add(new GuiScrollableList(50, 100, w - 100, h - 250, list));
		
		this.buttonList.add(new ReturnButton(w/2, h - 75, this));
	}
	
	public void render(GameContainer gc, Graphics g, Vector2f origin) throws SlickException {
		GuiUtils.renderDefaultBackground(g);
		this.moneyDisplayer.render(gc, g);
		super.render(gc, g, origin);
	}

	public void update(GameContainer gc, int delta, Vector2f origin) throws SlickException {
		super.update(gc, delta, origin);
		moneyDisplayer.update();
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
