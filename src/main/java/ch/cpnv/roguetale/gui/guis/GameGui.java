package ch.cpnv.roguetale.gui.guis;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import ch.cpnv.roguetale.controller.AreaController;
import ch.cpnv.roguetale.controller.EnemyController;
import ch.cpnv.roguetale.controller.GuiController;
import ch.cpnv.roguetale.controller.MapController;
import ch.cpnv.roguetale.controller.PickupableItemController;
import ch.cpnv.roguetale.controller.PlayerController;
import ch.cpnv.roguetale.controller.ProjectileController;
import ch.cpnv.roguetale.controller.UiController;
import ch.cpnv.roguetale.entity.character.Player;
import ch.cpnv.roguetale.gui.Gui;
import ch.cpnv.roguetale.main.Main;

public class GameGui extends Gui {

	public GameGui(Gui prevGui) {
		super(prevGui);
		try {
			this.init();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	private void init() throws SlickException {
		PlayerController.getInstance().init();
		//PlayerController.getInstance().getPlayer().setInvulnerable(true);
		//PlayerController.getInstance().getPlayer().setOneHitKill();
	}
	
	public void render(GameContainer gc, Graphics g, Vector2f o) throws SlickException {
		Vector2f origin = getSlickOrigin();
		
		MapController.getInstance().render(gc, g, origin);
		AreaController.getInstance().render(gc, g, origin);
		PickupableItemController.getInstance().render(gc, g, origin);
		EnemyController.getInstance().render(gc, g, origin);
		PlayerController.getInstance().render(gc, g, origin);
		ProjectileController.getInstance().render(gc, g, origin);
		UiController.getInstance().render(gc, g, origin);
		
		super.render(gc, g, origin);
	}

	public void update(GameContainer gc, int delta, Vector2f o) throws SlickException {
		Vector2f origin = getSlickOrigin();
		
		if (!PlayerController.getInstance().getPlayer().isDead()) {
			
			MapController.getInstance().update(gc, delta, origin);
			EnemyController.getInstance().update(gc, delta, origin);
			PlayerController.getInstance().update(gc, delta, origin);
			ProjectileController.getInstance().update(gc, delta, origin);
			AreaController.getInstance().update(gc, delta, origin);
		}
		
		UiController.getInstance().update(gc, delta, origin);

		super.update(gc, delta, origin);
	}

	public void keyReleased(int key, char c, GameContainer gc) throws SlickException {
		if (!PlayerController.getInstance().getPlayer().isDead()) {
			PlayerController.getInstance().keyReleased(key, c, gc);
		}
		
		if (Input.KEY_ESCAPE == key) {
			GuiController.getInstance().setDisplayGui(new OptionGui(this));
		}

		super.keyReleased(key, c, gc);
	}

	public void keyPressed(int key, char c, GameContainer gc) throws SlickException {
		if (!PlayerController.getInstance().getPlayer().isDead()) {
			PlayerController.getInstance().keyPressed(key, c, gc);
		}
		
		super.keyPressed(key, c, gc);
	}

	public void mousePressed(int button, int x, int y) throws SlickException {
		if (!PlayerController.getInstance().getPlayer().isDead()) {
			PlayerController.getInstance().mousePressed(button, x, y);
		}

		super.mousePressed(button, x, y);
	}
	
	public void mouseMoved(int oldx, int oldy, int newx, int newy) {
		super.mouseMoved(oldx, oldy, newx, newy);
	}

	// Get the coordinate of the UP LEFT corner of the screen
	protected Vector2f getSlickOrigin() throws SlickException {
		Player player = PlayerController.getInstance().getPlayer();
		return new Vector2f(player.getPosition().x - Main.BASE_WIDTH/2, player.getPosition().y + Main.BASE_HEIGHT/2);
	}
}
