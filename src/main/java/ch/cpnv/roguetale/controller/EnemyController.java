package ch.cpnv.roguetale.controller;

import java.util.ArrayList;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import ch.cpnv.roguetale.entity.character.Enemy;
import ch.cpnv.roguetale.entity.character.Player;
import ch.cpnv.roguetale.entity.character.enemy.Bomber;
import ch.cpnv.roguetale.entity.character.enemy.Robot;
import ch.cpnv.roguetale.gui.guis.GameGui;

public class EnemyController implements Controller {
	private final int MAX_ENEMIES = 3;
	private final int DISTANCE_NEAR_PLAYER = 500;
	private final int SPAWN_DISTANCE_MIN = 350;
	private final int SPAWN_DISTANCE_MAX = 450;
	
	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	
	public ArrayList<Enemy> getEnemies() {
		return enemies;
	}
	
	public void removeEnemy(Enemy enemy) {
		enemies.remove(enemy);
	}

	@Override
	public void render(GameContainer gc, Graphics g, Vector2f origin) throws SlickException {
		for (Enemy en : this.enemies) {
			en.draw(origin, gc);
		}
	}

	@Override
	public void update(GameContainer gc, int delta, Vector2f origin) throws SlickException {		
		for(Enemy enemy : enemies) {
			enemy.chooseAction();
			if (enemy.isMoving())
				enemy.move(delta);
			enemy.reduceCooldown(delta);
			enemy.update(delta);
		}
		
		spawnEnemies();
		
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
	
	private void spawnEnemies() throws SlickException {
		if (countEnemiesAroundPlayer() < MAX_ENEMIES) {
			Player p = GameGui.getPlayerController().getPlayer();
			Vector2f position = getRandomPositionNearPlayer(p);
			Enemy en = createRandomEnemy(position);
			
			if (en.isCollidingWithAnotherCharacter()) {
				// Respawn somewhere else if spawning on another entity
				this.spawnEnemies();
			} else if (en.getDistanceToMovableItem(p) > SPAWN_DISTANCE_MIN) {
				this.enemies.add(en);	
			}
		}
	}

	private int countEnemiesAroundPlayer() throws SlickException {
		Player p = GameGui.getPlayerController().getPlayer();
		int count = 0;
		
		for (Enemy en : this.enemies) {
			if (en.getDistanceToMovableItem(p) < DISTANCE_NEAR_PLAYER)
				count++;
		}
		
		return count;
	}
	
	private Vector2f getRandomPositionNearPlayer(Player p) {
		float posX = (float) (Math.random() * (SPAWN_DISTANCE_MAX) * (Math.random()<0.5 ? -1 : 1));
		float posY = (float) (Math.random() * (SPAWN_DISTANCE_MAX) * (Math.random()<0.5 ? -1 : 1));
		Vector2f relativePosition = new Vector2f(posX, posY);
		Vector2f position = Vector2f.add(p.getPosition(), relativePosition, null);
		
		return position;
	}
	
	private Enemy createRandomEnemy(Vector2f position) throws SlickException {
		Enemy enemy;
		int rand = (int) (Math.random() * 100);
		
		if (rand < 80) 
			enemy = new Robot(position);
		else
			enemy = new Bomber(position);
		
		return enemy;
	}

	@Override
	public void mouseMoved(int oldx, int oldy, int newx, int newy) {
		// TODO Auto-generated method stub
		
	}
}
