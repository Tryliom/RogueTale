package ch.cpnv.roguetale.gui.guis;

import java.io.IOException;
import java.util.Properties;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import ch.cpnv.roguetale.config.ConfigReader;
import ch.cpnv.roguetale.controller.AreaController;
import ch.cpnv.roguetale.controller.EnemyController;
import ch.cpnv.roguetale.controller.GuiController;
import ch.cpnv.roguetale.controller.MapController;
import ch.cpnv.roguetale.controller.MeleeAttackController;
import ch.cpnv.roguetale.controller.PickupableItemController;
import ch.cpnv.roguetale.controller.PlayerController;
import ch.cpnv.roguetale.controller.ProjectileController;
import ch.cpnv.roguetale.controller.UiController;
import ch.cpnv.roguetale.entity.character.Player;
import ch.cpnv.roguetale.gui.Gui;
import ch.cpnv.roguetale.main.Game;
import ch.cpnv.roguetale.main.Main;
import ch.cpnv.roguetale.sound.SoundManager;
import ch.cpnv.roguetale.sound.SoundType;

public class GameGui extends Gui {
	private static PlayerController playerController;
	private static MapController mapController;
	private static AreaController areaController;
	private static EnemyController enemyController;
	private static ProjectileController projectileController;
	private static MeleeAttackController meleeAttackController;
	private static UiController uiController;
	private static PickupableItemController pickupableItemController;

	public GameGui(Gui prevGui) {
		super(prevGui);
		try {
			GameGui.playerController = new PlayerController();
			GameGui.mapController = new MapController();
			GameGui.areaController = new AreaController();
			GameGui.enemyController = new EnemyController();
			GameGui.projectileController = new ProjectileController();
			GameGui.uiController = new UiController();
			GameGui.pickupableItemController = new PickupableItemController();
			GameGui.meleeAttackController = new MeleeAttackController();
			
			Properties config = new ConfigReader().getProperties();
			if (!config.isEmpty()) {
				
				Boolean invulnerable = Boolean.parseBoolean(config.getProperty("invulnerable"));
				if (invulnerable) {
					GameGui.playerController.getPlayer().setInvulnerable(true);
				}
				
				Boolean oneHitKill = Boolean.parseBoolean(config.getProperty("onehitkill"));
				if (oneHitKill) {
					GameGui.playerController.getPlayer().setOneHitKill();
				}
				
				Boolean debug = Boolean.parseBoolean(config.getProperty("debug"));
				if (debug) {
					Game.getInstance().setDebug(debug);
				}
			}
		} catch (SlickException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void render(GameContainer gc, Graphics g, Vector2f o) throws SlickException {
		Vector2f origin = getSlickOrigin();
		
		GameGui.mapController.render(gc, g, origin);
		GameGui.areaController.render(gc, g, origin);
		GameGui.pickupableItemController.render(gc, g, origin);
		GameGui.enemyController.render(gc, g, origin);
		GameGui.playerController.render(gc, g, origin);
		GameGui.meleeAttackController.render(gc, g, origin);
		GameGui.projectileController.render(gc, g, origin);
		GameGui.uiController.render(gc, g, origin);
		
		super.render(gc, g, origin);
	}

	public void update(GameContainer gc, int delta, Vector2f o) throws SlickException {
		Vector2f origin = getSlickOrigin();
		
		if (!GameGui.playerController.getPlayer().isDead()) {
			
			GameGui.mapController.update(gc, delta, origin);
			GameGui.enemyController.update(gc, delta, origin);
			GameGui.playerController.update(gc, delta, origin);
			GameGui.projectileController.update(gc, delta, origin);
			GameGui.meleeAttackController.update(gc, delta, origin);
			GameGui.areaController.update(gc, delta, origin);
		} else {
			GuiController.getInstance().setDisplayGui(new GameOverGui(this));
		}
		
		GameGui.uiController.update(gc, delta, origin);

		super.update(gc, delta, origin);
	}

	public void keyReleased(int key, char c, GameContainer gc) throws SlickException {
		if (!GameGui.playerController.getPlayer().isDead()) {
			GameGui.playerController.keyReleased(key, c, gc);
		}
		
		if (Input.KEY_ESCAPE == key) {
			GuiController.getInstance().setDisplayGui(new InGameMenuGui(this));
			SoundManager.getInstance().stop(SoundType.MainTheme);
		}

		super.keyReleased(key, c, gc);
	}

	public void keyPressed(int key, char c, GameContainer gc) throws SlickException {
		if (!GameGui.playerController.getPlayer().isDead()) {
			GameGui.playerController.keyPressed(key, c, gc);
		}
		
		super.keyPressed(key, c, gc);
	}

	public void mousePressed(int button, int x, int y) throws SlickException {
		if (!GameGui.playerController.getPlayer().isDead()) {
			GameGui.playerController.mousePressed(button, x, y);
		}

		super.mousePressed(button, x, y);
	}
	
	public void mouseMoved(int oldx, int oldy, int newx, int newy) {
		super.mouseMoved(oldx, oldy, newx, newy);
	}
	
	public void mouseReleased(int button, int x, int y) throws SlickException {
		if (!GameGui.playerController.getPlayer().isDead()) {
			GameGui.playerController.mouseReleased(button, x, y);
		}
		super.mouseReleased(button, x, y);
		
	}

	// Get the coordinate of the UP LEFT corner of the screen
	private Vector2f getSlickOrigin() throws SlickException {
		Player player = GameGui.playerController.getPlayer();
		return new Vector2f(player.getPosition().x - Main.BASE_WIDTH/2, player.getPosition().y + Main.BASE_HEIGHT/2);
	}

	public static PlayerController getPlayerController() {
		return playerController;
	}

	public static MapController getMapController() {
		return mapController;
	}

	public static AreaController getAreaController() {
		return areaController;
	}

	public static EnemyController getEnemyController() {
		return enemyController;
	}

	public static ProjectileController getProjectileController() {
		return projectileController;
	}
	
	public static MeleeAttackController getMeleeAttackController() {
		return meleeAttackController;
	}

	public static UiController getUiController() {
		return uiController;
	}

	public static PickupableItemController getPickupableItemController() {
		return pickupableItemController;
	}
	
	
}
