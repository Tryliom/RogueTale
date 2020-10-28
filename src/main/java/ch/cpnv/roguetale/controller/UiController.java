package ch.cpnv.roguetale.controller;

import java.util.ArrayList;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import ch.cpnv.roguetale.entity.character.Player;
import ch.cpnv.roguetale.entity.ui.UiLifePoint;
import ch.cpnv.roguetale.entity.ui.UiWeaponSlot;
import ch.cpnv.roguetale.gui.guis.GameGui;
import ch.cpnv.roguetale.main.Main;
import ch.cpnv.roguetale.weapon.Weapon;

public class UiController implements Controller {
	protected static final int Y_POSITION = 5;
	protected static final int X_POSITION = 5;
	private static final String MOUSE_LEFT_PATH = "ch\\cpnv\\roguetale\\images\\ui\\icon\\mouseLeft.png";
	private static final String MOUSE_RIGHT_PATH = "ch\\cpnv\\roguetale\\images\\ui\\icon\\mouseRight.png";
	
	protected ArrayList<UiLifePoint> lifePoints = new ArrayList<UiLifePoint>();
	protected ArrayList<UiWeaponSlot> weapons = new ArrayList<UiWeaponSlot>();

	public UiController() {
		Player player = GameGui.getPlayerController().getPlayer();
		weapons.add(new UiWeaponSlot(Main.BASE_WIDTH/4, Main.BASE_HEIGHT - 10, MOUSE_LEFT_PATH, player.getPrimaryWeapon()));
		weapons.add(new UiWeaponSlot(Main.BASE_WIDTH*3/4, Main.BASE_HEIGHT - 10, MOUSE_RIGHT_PATH, player.getSecondaryWeapon()));
	}
	
	@Override
	public void render(GameContainer gc, Graphics g, Vector2f origin) throws SlickException {
		int x = X_POSITION;
		for(UiLifePoint lifePoint : lifePoints) {
			lifePoint.getSprite().draw(x, Y_POSITION);
			x += lifePoint.getSprite().getWidth();
		}
		
		for (UiWeaponSlot weapon : weapons) {
			weapon.render(gc, g, origin);
		}
	}

	@Override
	public void update(GameContainer gc, int delta, Vector2f origin) throws SlickException {
		// Set the correct number of lifePoints
		Player player = GameGui.getPlayerController().getPlayer();
		while (lifePoints.size() < player.getMaxHealth()) {
			lifePoints.add(new UiLifePoint());
		}
		if (lifePoints.size() > player.getMaxHealth()) {
			lifePoints = new ArrayList<UiLifePoint>(lifePoints.subList(0, player.getMaxHealth() - 1));
		}
		
		// Set the content of the lifePoints : full/empty
		int remainingFullLifePoints = player.getCurrentHealth();
		for(UiLifePoint lifePoint : lifePoints) {
			if(remainingFullLifePoints > 0) {
				if(!lifePoint.isFull()) {
					lifePoint.setFull(true);
				}
				remainingFullLifePoints--;
			}
			else {
				if(lifePoint.isFull()) {
					lifePoint.setFull(false);
				}
			}
		}
		
		Weapon primary = player.getPrimaryWeapon();
		UiWeaponSlot first = weapons.get(0);
		Weapon secondary = player.getSecondaryWeapon();
		UiWeaponSlot second = weapons.get(1);
		
		first.setWeapon(primary);
		second.setWeapon(secondary);
	}

	@Override
	public void keyReleased(int key, char c, GameContainer gc) {
		// Do nothing
	}

	@Override
	public void keyPressed(int key, char c, GameContainer gc) {
		// Do nothing
	}

	@Override
	public void mousePressed(int button, int x, int y) {
		// Do nothing
	}

	@Override
	public void mouseMoved(int oldx, int oldy, int newx, int newy) {
		// TODO Auto-generated method stub
		
	}

}
