package ch.cpnv.roguetale.controller;

import java.util.ArrayList;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import ch.cpnv.roguetale.entity.character.Player;
import ch.cpnv.roguetale.entity.ui.UiLifePoint;
import ch.cpnv.roguetale.gui.guis.GameGui;

public class UiController implements Controller {
	protected static final int Y_POSITION = 5;
	protected static final int X_POSITION = 5;
	
	protected ArrayList<UiLifePoint> lifePoints = new ArrayList<UiLifePoint>();

	@Override
	public void render(GameContainer gc, Graphics g, Vector2f origin) throws SlickException {
		int x = X_POSITION;
		for(UiLifePoint lifePoint : lifePoints) {
			lifePoint.getSprite().draw(x, Y_POSITION);
			x += lifePoint.getSprite().getWidth();
		}
	}

	@Override
	public void update(GameContainer gc, int delta, Vector2f origin) throws SlickException {
		// Set the correct number of lifePoints
		Player player = GameGui.getPlayerController().getPlayer();
		while (lifePoints.size() < player.getMaxHealth()) {
			lifePoints.add(new UiLifePoint());
		}
		if (lifePoints.size() > player.getMaxHealth()) {
			lifePoints = new ArrayList<UiLifePoint>(lifePoints.subList(0, player.getMaxHealth() - 1));
		}
		
		// Set the content of the lifePoints : full/empty
		int remainingFullLifePoints = player.getCurrentHealth();
		for(UiLifePoint lifePoint : lifePoints) {
			if(remainingFullLifePoints > 0) {
				if(!lifePoint.isFull()) {
					lifePoint.setFull(true);
				}
				remainingFullLifePoints--;
			}
			else {
				if(lifePoint.isFull()) {
					lifePoint.setFull(false);
				}
			}
		}	
	}

	@Override
	public void keyReleased(int key, char c, GameContainer gc) {
		// Do nothing
	}

	@Override
	public void keyPressed(int key, char c, GameContainer gc) {
		// Do nothing
	}

	@Override
	public void mousePressed(int button, int x, int y) {
		// Do nothing
	}

	@Override
	public void mouseMoved(int oldx, int oldy, int newx, int newy) {
		// TODO Auto-generated method stub
		
	}

	public void init() {
		lifePoints.clear();
		
	}

}
