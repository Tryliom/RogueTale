package ch.cpnv.roguetale.gui.guis;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import ch.cpnv.roguetale.gui.Gui;
import ch.cpnv.roguetale.gui.button.buttons.PlayButton;
import ch.cpnv.roguetale.gui.button.buttons.ReturnToMenuButton;
import ch.cpnv.roguetale.gui.texts.GuiLabel;
import ch.cpnv.roguetale.main.Main;
import ch.cpnv.roguetale.sound.SoundManager;
import ch.cpnv.roguetale.sound.SoundType;

public class GameOverGui extends Gui {

	public GameOverGui(Gui prevGui) throws SlickException {
		super(prevGui);
		this.init();
	}
	
	public void init() throws SlickException {
		this.buttonList.add(new PlayButton("Rejouer", Main.BASE_WIDTH/2, Main.BASE_HEIGHT*5/8, this));
		this.buttonList.add(new ReturnToMenuButton(Main.BASE_WIDTH/2, Main.BASE_HEIGHT*3/4, this));
		this.labelList.add(new GuiLabel("Game Over", Main.BASE_WIDTH/2, Main.BASE_HEIGHT/4, new Color(190, 83, 83)));
		SoundManager.getInstance().stop(SoundType.MainTheme);
		SoundManager.getInstance().play(SoundType.GameOver);
	}
	
	public void render(GameContainer gc, Graphics g, Vector2f origin) throws SlickException {
		this.prevGui.render(gc, g, origin);
		super.render(gc, g, origin);
	}

	public void update(GameContainer gc, int delta, Vector2f origin) throws SlickException {
		super.update(gc, delta, origin);

	}

	public void keyReleased(int key, char c, GameContainer gc) throws SlickException {
		super.keyReleased(key, c, gc);
	}

	public void keyPressed(int key, char c, GameContainer gc) throws SlickException {
		super.keyPressed(key, c, gc);

	}

	public void mousePressed(int button, int x, int y) throws SlickException {
		super.mousePressed(button, x, y);

	}
	
	public void mouseMoved(int oldx, int oldy, int newx, int newy) {
		super.mouseMoved(oldx, oldy, newx, newy);
	}

}
