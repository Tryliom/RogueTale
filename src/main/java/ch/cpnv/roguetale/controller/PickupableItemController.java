package ch.cpnv.roguetale.controller;

import java.util.ArrayList;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import ch.cpnv.roguetale.entity.pickupableitem.PickupableItem;

public class PickupableItemController implements Controller {
	private ArrayList<PickupableItem> pickupableItems = new ArrayList<PickupableItem>();
	
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

	@Override
	public void mouseMoved(int oldx, int oldy, int newx, int newy) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(int button, int x, int y) throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseWheelMoved(int score) {
		// TODO Auto-generated method stub
		
	}

}
