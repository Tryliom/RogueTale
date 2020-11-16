package ch.cpnv.roguetale.weapon.other;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import ch.cpnv.roguetale.entity.character.Character;
import ch.cpnv.roguetale.entity.character.Enemy;
import ch.cpnv.roguetale.entity.character.enemy.Bomber;
import ch.cpnv.roguetale.entity.character.enemy.Robot;
import ch.cpnv.roguetale.gui.guis.GameGui;
import ch.cpnv.roguetale.weapon.Weapon;

public class CreationOfLife extends Weapon {
	private static final String ICON_PATH = "ch\\cpnv\\roguetale\\images\\ui\\icon\\creationoflife.png";
	private static final int SPAWN_DISTANCE_MAX = 200;
	private static final int DISTANCE_NEAR_USER = 500;
	private static final int MAX_ENEMIES = 5;

	public CreationOfLife() throws SlickException {
		super("Créateur de vie", 0, 5000, new Image(ICON_PATH));
	}
	
	public void attack(Character attacker) throws SlickException {		
		if (canAttack()) {
			this.spawnEnemies(attacker);
		}
		
		super.attack(attacker);
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
	
	private Vector2f getRandomNearUser(Character user) {
		float posX = (float) (Math.random() * (SPAWN_DISTANCE_MAX) * (Math.random()<0.5 ? -1 : 1));
		float posY = (float) (Math.random() * (SPAWN_DISTANCE_MAX) * (Math.random()<0.5 ? -1 : 1));
		Vector2f relativePosition = new Vector2f(posX, posY);
		Vector2f position = Vector2f.add(user.getPosition(), relativePosition, null);
		
		return position;
	}

	private void spawnEnemies(Character user) throws SlickException {
		if (this.countEnemiesAroundUser(user) < MAX_ENEMIES) {
			Enemy entity = createRandomEnemy(getRandomNearUser(user));
			
			if (entity.getCollidingCharacter() != null && entity.getCollidingObstacle() != null) {
				// Respawn somewhere else if spawning on another entity
				this.spawnEnemies(user);
			} else {
				entity.setFaction(user.getFaction());
				GameGui.getEnemyController().addEnemyToAdd(entity);
			}
		}
	}
	
	private int countEnemiesAroundUser(Character user) throws SlickException {
		int count = 0;
		
		for (Character en : user.getCharacterList()) {
			if (user.getFaction().getId() != en.getFaction().getId() && en.getDistanceToMovableItem(user) < DISTANCE_NEAR_USER)
				count++;
		}
		
		return count;
	}
}
