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
	public void render(GameContainer gc, Graphics g, Vector2f origin, Player p) throws SlickException {
		for (Enemy en : this.enemies) {
			en.draw(origin, gc);
		}

	}

	@Override
	public void update(GameContainer gc, int delta, Player p) throws SlickException {
		for (Enemy en : this.enemies) {
			if (en.isFaceToPlayer(p) && en.isInPlayerRange(p))
				en.act(p);
			else 
				en.moveTowardPlayer(p);
			if (en.isMoving())
				en.move(delta);
			en.reduceCooldown(delta);
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
