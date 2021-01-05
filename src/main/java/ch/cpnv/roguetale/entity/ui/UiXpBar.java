package ch.cpnv.roguetale.entity.ui;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

import ch.cpnv.roguetale.entity.character.Player;
import ch.cpnv.roguetale.gui.GuiUtils;
import ch.cpnv.roguetale.gui.guis.GameGui;

public class UiXpBar {
	private int actualXp;
	private int maxXp;
	private int level;
	
	private static final int BAR_WIDTH = 200;
	private static final int BAR_HEIGHT = 20;
	
	public void render(Vector2f origin, GameContainer gc) {
		int x = 5;
		int y = 50;
		Rectangle bar = new Rectangle(x, y, BAR_WIDTH, BAR_HEIGHT);
		Graphics g = gc.getGraphics();
		
		GuiUtils.renderBar(bar, Color.darkGray, Color.white, Color.green, 1, (float) this.actualXp / this.maxXp, g);
		Color old = g.getColor();
		g.setColor(Color.white);
		g.drawString("Niveau "+this.level, x + BAR_WIDTH + 5, y - 3);
		g.setColor(old);
	}

	public void update(int delta) throws SlickException {
		Player player = GameGui.getPlayerController().getPlayer();
		
		this.actualXp = player.getCurrentExp();
		this.maxXp = player.getMaxExp();
		this.level = player.getLevel();
	}

}
