package ch.cpnv.roguetale.controller;

import java.util.ArrayList;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import ch.cpnv.roguetale.entity.character.Ability;
import ch.cpnv.roguetale.entity.character.Player;
import ch.cpnv.roguetale.entity.ui.UiMoney;
import ch.cpnv.roguetale.entity.ui.UiAbility;
import ch.cpnv.roguetale.entity.ui.UiLifePoint;
import ch.cpnv.roguetale.entity.ui.UiWeaponSlot;
import ch.cpnv.roguetale.entity.ui.UiXpBar;
import ch.cpnv.roguetale.gui.guis.GameGui;
import ch.cpnv.roguetale.main.Main;
import ch.cpnv.roguetale.weapon.Weapon;

public class UiController implements Controller {
	protected static final int LIFEPOINT_Y_POSITION = 5;
	protected static final int LIFEPOINT_X_POSITION_START = 5;
	protected static final int MONEY_Y_POSITION = LIFEPOINT_Y_POSITION;
	protected static final int MONEY_X_POSITION = Main.BASE_WIDTH - 50;
	protected static final int ACTIONS_Y_POSITION = Main.BASE_HEIGHT - 10;
	protected static final int LEFT_WEAPON_X_POSITION = Main.BASE_WIDTH / 4;
	protected static final int RIGHT_WEAPON_X_POSITION =  Main.BASE_WIDTH * 3/4;
	protected static final int ACTIONS_X_DISTANCE = 100;
	private static final String MOUSE_LEFT_PATH = "ch\\cpnv\\roguetale\\images\\ui\\icon\\mouseLeft.png";
	private static final String MOUSE_RIGHT_PATH = "ch\\cpnv\\roguetale\\images\\ui\\icon\\mouseRight.png";
	
	protected ArrayList<UiLifePoint> lifePoints = new ArrayList<UiLifePoint>();
	protected ArrayList<UiWeaponSlot> weapons = new ArrayList<UiWeaponSlot>();
	protected ArrayList<UiAbility> abilities = new ArrayList<UiAbility>();
	protected UiXpBar xpBar;
	protected UiMoney moneyDisplayer;

	public UiController() {
		Player player = GameGui.getPlayerController().getPlayer();
		weapons.add(new UiWeaponSlot(LEFT_WEAPON_X_POSITION, ACTIONS_Y_POSITION, MOUSE_LEFT_PATH, player.getPrimaryWeapon()));
		weapons.add(new UiWeaponSlot(RIGHT_WEAPON_X_POSITION, ACTIONS_Y_POSITION, MOUSE_RIGHT_PATH, player.getSecondaryWeapon()));
		for(Ability ability : player.getAbilities()) {
			addAbility(ability);
		}	
		this.xpBar = new UiXpBar();
		try {
			moneyDisplayer = new UiMoney(MONEY_X_POSITION, MONEY_Y_POSITION);
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void render(GameContainer gc, Graphics g, Vector2f origin) throws SlickException {
		int x = LIFEPOINT_X_POSITION_START;
		for(UiLifePoint lifePoint : lifePoints) {
			lifePoint.getSprite().draw(x, LIFEPOINT_Y_POSITION);
			x += lifePoint.getSprite().getWidth();
		}
		
		for (UiWeaponSlot weapon : weapons) {
			weapon.render(gc, g, origin);
		}
		
		for (UiAbility ability : abilities) {
			ability.render(gc, g, origin);
		}
		
		this.xpBar.render(origin, gc);
		moneyDisplayer.render(gc, g);
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
		
		// Set the weapons
		Weapon primary = player.getPrimaryWeapon();
		UiWeaponSlot first = weapons.get(0);
		Weapon secondary = player.getSecondaryWeapon();
		UiWeaponSlot second = weapons.get(1);
		
		first.setWeapon(primary);
		second.setWeapon(secondary);
		
		this.xpBar.update(delta);
		
		// Set the money
		moneyDisplayer.update();
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

	@Override
	public void mouseReleased(int button, int x, int y) throws SlickException {
		// TODO Auto-generated method stub
	}

	public void addAbility(Ability ability) {
		abilities.add(new UiAbility(LEFT_WEAPON_X_POSITION, ACTIONS_Y_POSITION, ability));
		setAbilitiesPosition();
	}
	
	public void removeAbility(Ability ability) {
		abilities.removeIf(uiAbility -> (uiAbility.getAbility() == ability));
		setAbilitiesPosition();
	}
	
	protected void setAbilitiesPosition() {
		int x = LEFT_WEAPON_X_POSITION;
		for(UiAbility ability : abilities) {
			x += ACTIONS_X_DISTANCE;
			ability.setX(x);
		}
	}
}
