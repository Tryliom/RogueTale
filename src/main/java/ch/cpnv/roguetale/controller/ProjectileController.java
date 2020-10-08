package ch.cpnv.roguetale.controller;

import java.util.ArrayList;
import java.util.Iterator;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import ch.cpnv.roguetale.entity.character.Character;
import ch.cpnv.roguetale.entity.projectile.Projectile;
import ch.cpnv.roguetale.gui.guis.GameGui;

public class ProjectileController implements Controller {
	private final ArrayList<Projectile> projectiles = new ArrayList<Projectile>();

	@Override
	public void render(GameContainer gc, Graphics g, Vector2f origin) throws SlickException {
		for(Projectile projectile : projectiles) {
			projectile.draw(origin, gc);
		}
	}

	@Override
	public void update(GameContainer gc, int delta, Vector2f origin) throws SlickException {
		moveProjectiles(delta);
		collideProjectiles();
		removeExpiredProjectiles();
		for(Projectile projectile : projectiles) {
			projectile.update(delta);
		}
	}

	@Override
	public void keyReleased(int key, char c, GameContainer gc) {
		// Nothing to do
	}

	@Override
	public void keyPressed(int key, char c, GameContainer gc) {
		// Nothing to do
	}
	
	@Override
	public void mousePressed(int button, int x, int y) {
		// Nothing to do
	}
	
	public void addProjectile(Projectile projectile) {
		projectiles.add(projectile);
	}
	
	private void moveProjectiles(int delta) throws SlickException {
		for(Projectile projectile : projectiles) {
			projectile.move(delta);
		}
	}
	
	private void collideProjectiles() throws SlickException {
		Character player = GameGui.getPlayerController().getPlayer();
		
		for(Projectile projectile : projectiles) {
			Character hit = null;
			if(projectile.isColliding(player)) {
				hit = player;
			}
			else {
				for(Character enemy : GameGui.getEnemyController().getEnemies()) {
					if(projectile.isColliding(enemy)) {
						hit = enemy;
						break;
					}
				}
			}
			
			if(hit != null) {
				projectile.meetCharacter(hit);
			}
		}
	}
	
	private void removeExpiredProjectiles() throws SlickException {
		// The remove method does not work in a "for(Projectile projectile : projectiles)" loop
		// https://stackoverflow.com/questions/3184883/concurrentmodificationexception-for-arraylist
		for(Iterator<Projectile> iterator = projectiles.iterator(); iterator.hasNext();) {
			Projectile projectile = iterator.next();
			if (projectile.isExpired()) {
				projectile.onDeath();
				iterator.remove();
			}
		}
	}

	@Override
	public void mouseMoved(int oldx, int oldy, int newx, int newy) {
		// TODO Auto-generated method stub
		
	}

	public void init() {
		projectiles.clear();
		
	}

}
