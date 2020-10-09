package ch.cpnv.roguetale.gui.button;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import ch.cpnv.roguetale.gui.Gui;
import ch.cpnv.roguetale.gui.enums.State;
import ch.cpnv.roguetale.sound.SoundManager;
import ch.cpnv.roguetale.sound.SoundType;

public class GuiButton {
	private static final String PATH_BTN = "ch\\cpnv\\roguetale\\images\\ui\\button\\btn1.png";
	private static final String PATH_BTN_HOVERED = "ch\\cpnv\\roguetale\\images\\ui\\button\\btn1_hovered.png";
	protected String content;
	protected int x;
	protected int y;
	protected int width;
	protected int height;
	protected State state;
	protected Gui parentGui;
	
	public GuiButton(int x, int y, Gui parentGui) {
		this.content = "";
		this.width = 200;
		this.x = x - this.width/2;
		this.y = y;
		this.height = 40;
		this.parentGui = parentGui;
		this.state = State.NONE;
	}
	
	public void render(GameContainer gc, Graphics g) throws SlickException {
		// Default display of a button
		Boolean hover = this.state.equals(State.HOVERED);
		Image btn = new Image(hover ? PATH_BTN_HOVERED : PATH_BTN);
		btn.setFilter(Image.FILTER_NEAREST);
		
		btn.draw(x, y, width, height);
		
		Color old = g.getColor();
		g.setColor(new Color(195, 197, 213));
		int xCenter = x + width/2 - g.getFont().getWidth(content) / 2;
		g.drawString(content, xCenter, y + (height-g.getFont().getHeight(content))/2 - 2);
		g.setColor(old);
	}
	
	public void onClick() throws SlickException {
		SoundManager.getInstance().play(SoundType.Click, 0.2f);
	}
	
	public boolean isHoveringButton(int x, int y) {
		return this.x < x && this.x+this.width > x && this.y < y && this.y+this.height > y;
	}
	
	public void mouseHover(int oldx, int oldy, int newx, int newy) {
		State futurState = null;
		if (this.isHoveringButton(newx, newy)) {
			futurState = State.HOVERED;
		} else {
			futurState = State.NONE;
		}
		
		if (futurState != null && !futurState.equals(this.state)) {
			this.y += futurState.equals(State.HOVERED) ? 3 : -3;
			this.state = futurState;
		}
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}
	
	
}
