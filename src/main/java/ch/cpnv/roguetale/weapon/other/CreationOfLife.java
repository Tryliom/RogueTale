package ch.cpnv.roguetale.weapon.other;

import java.util.ArrayList;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import ch.cpnv.roguetale.entity.character.Character;
import ch.cpnv.roguetale.entity.character.Enemy;
import ch.cpnv.roguetale.entity.character.enemies.Bomber;
import ch.cpnv.roguetale.entity.character.enemies.Robot;
import ch.cpnv.roguetale.gui.guis.GameGui;
import ch.cpnv.roguetale.weapon.Weapon;

public class CreationOfLife extends Weapon {
	private static final String ICON_PATH = "ch\\cpnv\\roguetale\\images\\ui\\icon\\creationoflife.png";
	private static final int SPAWN_DISTANCE_MAX = 200;
	private int max_allies = 5;

	public CreationOfLife() throws SlickException {
		super("Créateur de vie", 0, 5000, 0, 0, new Image(ICON_PATH));
	}
	
	@Override
	public void attack(Character attacker) throws SlickException {		
		if (canAttack()) {
			// Max 10 tries to spawn entity
			this.spawnEnemies(attacker, 100);
		}
		
		super.attack(attacker);
	}
	
	@Override
	public String getDescription() {
		return "Invoque une unité alliée qui combattera pour vous du même niveau que vous jusqu'à "+max_allies+" alliés maximum.";
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

	private void spawnEnemies(Character user, int maxTries) throws SlickException {
		if (this.countAlliesAroundUser(user) < max_allies) {
			Enemy entity = createRandomEnemy(getRandomNearUser(user));
			int lvl = user.getLevel();
			for (int i = 0; i < lvl;i++) {
				entity.levelup();
			}
			
			if (entity.getCollidingCharacter() != null || entity.getToCreateCollidingCharacter() != null || entity.getCollidingObstacle() != null) {
				// Respawn somewhere else if spawning on another entity
				if (maxTries > 0)
					this.spawnEnemies(user, maxTries-1);
			} else {
				entity.setFaction(user.getFaction());
				GameGui.getEnemyController().addEnemyToAdd(entity);
			}
		}
	}
	
	private int countAlliesAroundUser(Character user) throws SlickException {
		int count = 0;
		
		for (Character en : user.getCharacterList()) {
			if (user.getFaction().getId() == en.getFaction().getId())
				count++;
		}
		
		return count;
	}
	
	public ArrayList<String> getCaracteristics() {
		ArrayList<String> list = super.getCaracteristics();
		int minAllyLvl = (this.tier-1) * 3;
		int currentAllies = 0;
		try {
			currentAllies = this.countAlliesAroundUser(GameGui.getPlayerController().getPlayer());
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		list.add("Alliés: "+currentAllies+"/"+max_allies);
		
		return list;
	}
	
	@Override
	public void upgradeTier() {
		super.upgradeTier();
		
		this.max_allies++;
	}
}
