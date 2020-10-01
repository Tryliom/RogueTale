package ch.cpnv.roguetale.controller;

import java.util.ArrayList;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import ch.cpnv.roguetale.entity.pickupableitem.PickupableItem;

public class PickupableItemController implements Controller {
	private static PickupableItemController instance = null;
	
	private ArrayList<PickupableItem> pickupableItems;
	
	public static PickupableItemController getInstance() throws SlickException {
		if(instance == null) {
			instance = new PickupableItemController();
		}
		return instance;
	}
	
	private PickupableItemController() {
		pickupableItems = new ArrayList<PickupableItem>();
	}
	
	public ArrayList<PickupableItem> getPickupableItems() {
		return pickupableItems;
	}
	
	public void addPickupableItem(PickupableItem pickupableItem) {
		pickupableItems.add(pickupableItem);
	}
	
	public void removePickupableItem(PickupableItem pickupableItem) {
		pickupableItems.remove(pickupableItem);
	}

	@Override
	public void render(GameContainer gc, Graphics g, Vector2f origin) throws SlickException {
		for (PickupableItem item : this.pickupableItems) {
			item.draw(origin, gc);
		}
	}

	@Override
	public void update(GameContainer gc, int delta, Vector2f origin) throws SlickException {
		// Nothing to do
	}

	@Override
	public void keyReleased(int key, char c, GameContainer gc) {
		// Nothing to do
	}

	@Override
	public void keyPressed(int key, char c, GameContainer gc) throws SlickException {
		// Nothing to do
	}

	@Override
	public void mousePressed(int button, int x, int y) throws SlickException {
		// Nothing to do
	}

}
