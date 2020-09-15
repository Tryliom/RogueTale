package ch.cpnv.roguetale.controller;

import java.util.ArrayList;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import ch.cpnv.roguetale.entity.character.Player;
import ch.cpnv.roguetale.entity.ui.LifePoint;

public class UiController implements Controller {
	protected ArrayList<LifePoint> lifePoints;
	
	public UiController() {
		lifePoints = new ArrayList<LifePoint>();
	}

	@Override
	public void render(GameContainer gc, Graphics g, Vector2f origin, Player p) throws SlickException {
		int LifePointWidth = 300;
		int x = 10;
		int y = 10;
		for(LifePoint lifePoint : lifePoints) {
			lifePoint.getSprite().draw(x, y);
			x += LifePointWidth;
		}

	}

	@Override
	public void update(GameContainer gc, int delta, Player p) throws SlickException {
		// Set the correct number of lifePoints
		while (lifePoints.size() < p.getMaxHealth()) {
			lifePoints.add(new LifePoint());
		}
		if (lifePoints.size() > p.getMaxHealth()) {
			lifePoints = new ArrayList<LifePoint>(lifePoints.subList(0, p.getMaxHealth() - 1));
		}
		
		// Set the content of the lifePoints : full/empty
		int remainingFullLifePoints = p.getCurrentHealth();
		for(LifePoint lifePoint : lifePoints) {
			if(remainingFullLifePoints > 0) {
				if(!lifePoint.Full()) {
					lifePoint.Full(true);
				}
				remainingFullLifePoints--;
			}
			else {
				if(lifePoint.Full()) {
					lifePoint.Full(false);
				}
			}
		}	
	}

	@Override
	public void keyReleased(int key, char c, GameContainer gc) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(int key, char c, GameContainer gc) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(int button, int x, int y) {
		// TODO Auto-generated method stub

	}

}
