package ch.cpnv.roguetale.entity.ui;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import ch.cpnv.roguetale.controller.MoneyController;
import ch.cpnv.roguetale.entity.DrawableItem;
import ch.cpnv.roguetale.gui.texts.GuiLabel;

public class UiMoney extends DrawableItem {
	protected static final int DISPLAY_DIMENSION = 12;
	static protected final String SPRITESHEET_PATH = "ch\\cpnv\\roguetale\\images\\ui\\money\\gold_1.png";
	
	protected GuiLabel text;
	
	public UiMoney(int x, int y) throws SlickException {
		super(new SpriteSheet(SPRITESHEET_PATH, 84, 84), new Vector2f(x - 25, y));
		this.image = this.image.getScaledCopy(DISPLAY_DIMENSION, DISPLAY_DIMENSION);
		text = new GuiLabel("", x + getImageWidth() * 2 - 15, y, Color.yellow);
	}
	
	public void render(GameContainer gc, Graphics g) {
		getSprite().draw(getXLeft(), getYTop());
		text.render(gc, g);
	}
	
	public void update() {
		text.setContent(
				Integer.toString(MoneyController.getInstance().getMoney())
				);
	}
}