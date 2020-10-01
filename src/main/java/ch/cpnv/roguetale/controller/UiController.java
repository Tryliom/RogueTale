package ch.cpnv.roguetale.controller;

import java.util.ArrayList;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import ch.cpnv.roguetale.entity.character.Player;
import ch.cpnv.roguetale.entity.ui.UiLifePoint;

public class UiController implements Controller {
	private static UiController instance = null;
	
	protected static final int Y_POSITION = 5;
	protected static final int X_POSITION = 5;
	
	protected ArrayList<UiLifePoint> lifePoints;
	
	public static UiController getInstance() throws SlickException {
		if(instance == null) {
			instance = new UiController();
		}
		return instance;
	}
	
	private UiController() {
		lifePoints = new ArrayList<UiLifePoint>();
	}

	@Override
	public void render(GameContainer gc, Graphics g, Vector2f origin) throws SlickException {
		int x = X_POSITION;
		for(UiLifePoint lifePoint : lifePoints) {
			lifePoint.getSprite().draw(x, Y_POSITION);
			x += lifePoint.getSprite().getWidth();
		}
		if(PlayerController.getInstance().getPlayer().isDead()) {
			this.displayGameOver(g);
		}
	}

	@Override
	public void update(GameContainer gc, int delta, Vector2f origin) throws SlickException {
		// Set the correct number of lifePoints
		Player player = PlayerController.getInstance().getPlayer();
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
	
	protected void displayGameOver(Graphics g) {
		// Define color before an action
		g.setColor(new Color(200, 60, 60));
		g.drawString("Game Over", 250, 20);
		// g.resetColor()
	}

}
