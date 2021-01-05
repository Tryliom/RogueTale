package ch.cpnv.roguetale.controller;

import java.io.IOException;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import ch.cpnv.roguetale.main.Main;
import ch.cpnv.roguetale.save.SaveManager;
import ch.cpnv.roguetale.save.SaveProgress;

public class MoneyController implements Controller {
	private static MoneyController instance;
	
	protected int money;
	
	public static MoneyController getInstance() {
		if(instance == null) {
			instance = new MoneyController();
		}
		return instance;
	}
	
	protected MoneyController() {
		SaveProgress progress = Main.saveController.getProgress();
		money = progress.getMoney();
	}
	
	public int getMoney() {
		return money;
	}
	
	public void addMoney(int amount) {
		updateMoney(money + amount);
	}
	
	public void removeMoney(int amount) {
		updateMoney(money - amount);
	}
	
	protected void updateMoney(int money) {
		this.money = money;
		SaveProgress progress = Main.saveController.getProgress();
		progress.setMoney(money);
		try {
			new SaveManager().saveProgress();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void render(GameContainer gc, Graphics g, Vector2f origin) throws SlickException {
		// Do nothing
	}

	@Override
	public void update(GameContainer gc, int delta, Vector2f origin) throws SlickException {
		// Do nothing
	}

	@Override
	public void keyReleased(int key, char c, GameContainer gc) throws SlickException {
		// Do nothing
	}

	@Override
	public void keyPressed(int key, char c, GameContainer gc) throws SlickException {
		// Do nothing
	}

	@Override
	public void mousePressed(int button, int x, int y) throws SlickException {
		// Do nothing
	}

	@Override
	public void mouseMoved(int oldx, int oldy, int newx, int newy) {
		// Do nothing
	}

	@Override
	public void mouseReleased(int button, int x, int y) throws SlickException {
		// Do nothing
	}

}
