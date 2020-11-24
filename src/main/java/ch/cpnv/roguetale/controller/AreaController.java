package ch.cpnv.roguetale.controller;

import java.util.ArrayList;
import java.util.Iterator;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import ch.cpnv.roguetale.entity.DrawableItem;
import ch.cpnv.roguetale.entity.damageable.Damageable;
import ch.cpnv.roguetale.entity.temporaryeffect.areaofeffect.AreaOfEffect;
import ch.cpnv.roguetale.gui.guis.GameGui;

public class AreaController implements Controller {
	private ArrayList<AreaOfEffect> areas = new ArrayList<AreaOfEffect>();

	@Override
	public void render(GameContainer gc, Graphics g, Vector2f origin) throws SlickException {
		for (AreaOfEffect area : this.areas) {
			area.draw(origin, gc);
		}
		
	}

	@Override
	public void update(GameContainer gc, int delta, Vector2f origin) throws SlickException {
		for (AreaOfEffect area : this.areas) {
			area.update(delta);
		}
		this.damageEntities();
		this.removeExpiredAreas();
	}

	@Override
	public void keyReleased(int key, char c, GameContainer gc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(int key, char c, GameContainer gc) throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(int button, int x, int y) throws SlickException {
		// TODO Auto-generated method stub
		
	}
	
	private void damageEntities() throws SlickException {
		ArrayList<DrawableItem> damageCandidates = new ArrayList<>();
		damageCandidates.add(GameGui.getPlayerController().getPlayer());
		damageCandidates.addAll(GameGui.getEnemyController().getEnemies());
		damageCandidates.addAll(GameGui.getMapController().getObstacles());
		
		for (AreaOfEffect area : this.areas) {
			for (DrawableItem damageCandidate : damageCandidates) {
				if (area.isColliding(damageCandidate) && !area.isInCooldown(damageCandidate)) {
					area.getCooldownEntites().put(damageCandidate, area.getDelay());
					if(damageCandidate instanceof Damageable) {
						Damageable damageable = (Damageable) damageCandidate;
						damageable.damage(area.getDamage());
					}
					
				}
			}
		}
	}

	private void removeExpiredAreas() {
		for(Iterator<AreaOfEffect> iterator = areas.iterator(); iterator.hasNext();) {
			AreaOfEffect area = iterator.next();
			if (area.isExpired()) {
				iterator.remove();
			}
		}
	}
	
	public void addArea(AreaOfEffect area) {
		areas.add(area);
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
