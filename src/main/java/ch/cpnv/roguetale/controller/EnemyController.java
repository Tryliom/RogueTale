package ch.cpnv.roguetale.controller;

import java.util.ArrayList;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import ch.cpnv.roguetale.entity.character.Enemy;
import ch.cpnv.roguetale.entity.character.Player;
import ch.cpnv.roguetale.entity.character.enemy.Robot;

public class EnemyController implements Controller {
	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();

	public EnemyController() throws SlickException {
		this.enemies.add(new Robot(new Vector2f(150, 150)));
	}

	@Override
	public void render(GameContainer gc, Graphics g, Player p) throws SlickException {
		Vector2f origin = new Vector2f(p.getPosition().x - gc.getWidth()/2, p.getPosition().y + gc.getHeight()/2);
		
		for (Enemy en : this.enemies) {
			en.draw(origin);
		}

	}

	@Override
	public void update(GameContainer gc, int delta, Player p) throws SlickException {
		for (Enemy en : this.enemies) {
			if (en.isMoving())
				en.move(delta);
			else
				en.moveTowardPlayer(p);
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

}
