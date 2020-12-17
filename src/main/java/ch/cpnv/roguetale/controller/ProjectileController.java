package ch.cpnv.roguetale.controller;

import java.util.ArrayList;
import java.util.Iterator;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import ch.cpnv.roguetale.entity.Direction;
import ch.cpnv.roguetale.entity.character.Character;
import ch.cpnv.roguetale.entity.character.states.buff.CancelProjectiles;
import ch.cpnv.roguetale.entity.character.states.buff.Phantom;
import ch.cpnv.roguetale.entity.character.states.buff.Reflection;
import ch.cpnv.roguetale.entity.obstacle.Obstacle;
import ch.cpnv.roguetale.entity.projectile.Projectile;

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
		for(Projectile projectile : projectiles) {
			Character hit = projectile.getCollidingCharacter();			
			
			if (hit != null && !hit.hasState(Phantom.class) && hit.getFaction().getId() != projectile.getAttacker().getFaction().getId()) {
				// If character have the cancelProjectile effect, check if it's blocked
				boolean takeDamage = true;
				boolean isReflected = false;
				Direction reflect = Direction.UP;
				
				if (hit.hasState(CancelProjectiles.class)) {
					CancelProjectiles state = (CancelProjectiles) hit.getState(CancelProjectiles.class);
					int rand = (int) (Math.random() * 100);
					
					if (state.getLuckPercent() > rand) {
						takeDamage = false;
					}
				}
				
				if (hit.hasState(Reflection.class)) {
					Reflection state = (Reflection) hit.getState(Reflection.class);
					if (state.getProtect().equals(projectile.getDirection())) {
						reflect = state.getReflectDirection();
						isReflected = true;
						takeDamage = false;
					}
				}
				
				if (takeDamage)
					projectile.meetCharacter(hit);
				else if (!isReflected)
					projectile.setRemainingTime(0);
				else {
					projectile.setRemainingTime(projectile.getRemainingTime()*3/2);
					projectile.setDirection(reflect);
					projectile.setSpeed(projectile.getSpeed()*3/2);
					projectile.setPositionFromCharacter(hit, reflect);
					projectile.setAttacker(hit);
				}
			}
		}
		
		for(Projectile projectile : projectiles) {
			Obstacle hit = projectile.getCollidingObstacle();
			if(hit != null) {
				projectile.meetObstacle(hit);
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

	@Override
	public void mouseReleased(int button, int x, int y) throws SlickException {
		// TODO Auto-generated method stub
		
	}

}
