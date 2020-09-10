package ch.cpnv.roguetale.controller;

import java.util.ArrayList;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import ch.cpnv.roguetale.entity.character.Player;
import ch.cpnv.roguetale.entity.projectile.Projectile;

public class ProjectileController implements Controller {
	private ArrayList<Projectile> projectiles;
	
	public ProjectileController() {
		projectiles = new ArrayList<Projectile>();
	}

	@Override
	public void render(GameContainer gc, Graphics g, Vector2f origin, Player p) throws SlickException {
		for(Projectile projectile : projectiles) {
			projectile.draw(origin, gc);
		}
	}

	@Override
	public void update(GameContainer gc, int delta, Player p) throws SlickException {
		for(Projectile projectile : projectiles) {
			projectile.move(delta);
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
	
	public void addProjectile(Projectile projectile) {
		projectiles.add(projectile);
	}

}
