package ch.cpnv.roguetale.controller;

import java.util.ArrayList;
import java.util.Iterator;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import ch.cpnv.roguetale.entity.character.Character;
import ch.cpnv.roguetale.entity.temporaryeffect.meleeattack.MeleeAttack;
import ch.cpnv.roguetale.gui.guis.GameGui;

public class MeleeAttackController implements Controller {
	private final ArrayList<MeleeAttack> attacks = new ArrayList<MeleeAttack>();

	@Override
	public void render(GameContainer gc, Graphics g, Vector2f origin) throws SlickException {
		for(MeleeAttack attack : attacks) {
			attack.draw(origin, gc);
		}
	}

	@Override
	public void update(GameContainer gc, int delta, Vector2f origin) throws SlickException {
		removeExpiredAttacks();
		for(MeleeAttack attack : attacks) {
			attack.updateRemainingTime(-delta);
		}
	}

	@Override
	public void keyReleased(int key, char c, GameContainer gc) throws SlickException {
		// Nothing to do
	}

	@Override
	public void keyPressed(int key, char c, GameContainer gc) throws SlickException {
		// Nothing to do
	}

	@Override
	public void mousePressed(int button, int x, int y) throws SlickException {
		// Nothing to do
	}

	@Override
	public void mouseMoved(int oldx, int oldy, int newx, int newy) {
		// Nothing to do
	}
	
	public void addAttack(MeleeAttack attack) throws SlickException {
		attacks.add(attack);
		collideAttack(attack);
	}
	
	private void removeExpiredAttacks() {
		for(Iterator<MeleeAttack> iterator = this.attacks.iterator(); iterator.hasNext();) {
			MeleeAttack item = iterator.next();
			if (item.getRemainingTime() <= 0) {
				iterator.remove();
			}
		}
	}
	
	private void collideAttack(MeleeAttack attack) throws SlickException {
		Character player = GameGui.getPlayerController().getPlayer();
		if(attack.isColliding(player)) {
			attack.meetCharacter(player);
		}
		for(Character enemy : GameGui.getEnemyController().getEnemies()) {
			if(attack.isColliding(enemy)) {
				attack.meetCharacter(enemy);
			}
		}
	}
}
