package ch.cpnv.roguetale.gui.button;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

import ch.cpnv.roguetale.gui.Gui;
import ch.cpnv.roguetale.gui.GuiUtils;
import ch.cpnv.roguetale.gui.enums.State;
import ch.cpnv.roguetale.main.Game;
import ch.cpnv.roguetale.sound.SoundManager;
import ch.cpnv.roguetale.sound.SoundType;

public class GuiButton {
	protected String content;
	protected int x;
	protected int y;
	protected int width;
	protected int height;
	protected State state;
	protected Gui parentGui;
	protected boolean disabled;
	protected ArrayList<String> tooltip;
	
	public GuiButton(int x, int y, Gui parentGui) {
		this.content = "";
		this.width = 200;
		this.x = x - this.width/2;
		this.y = y;
		this.height = 40;
		this.parentGui = parentGui;
		this.state = State.NONE;
		this.disabled = false;
	}
	
	public void render(GameContainer gc, Graphics g) throws SlickException {
		// Default display of a button
		Boolean hover = this.state.equals(State.HOVERED);
		Rectangle rect = new Rectangle(x, y, width, height);
		
		GuiUtils.renderBox(rect, this.disabled ? new Color(50, 50, 50) : new Color(0,106,188, hover ? 255 : 200), this.disabled ? new Color(50, 50, 50) : new Color(0,48,131, hover ? 200 : 150), 3, g);
		
		Color old = g.getColor();
		g.setColor(new Color(195, 197, 213));
		int xCenter = x + width/2 - g.getFont().getWidth(content) / 2;
		g.drawString(content, xCenter, y + (height-g.getFont().getHeight(content))/2 - 2);
		g.setColor(old);
		g.resetLineWidth();
	}
	
	public void renderTooltip(Graphics g) {
		Boolean hover = this.state.equals(State.HOVERED);
		Color old = g.getColor();
		
		if (this.tooltip != null && hover) {
			int maxWidth = 0;
			int maxHeight = 10 * 2 + this.tooltip.size() * 20;
			
			for (String line : this.tooltip) {
				if (g.getFont().getWidth(line) > maxWidth) {
					maxWidth = g.getFont().getWidth(line);
				}
			}
			
			int positionOfTooltipY = 1;
			if (y - (height + maxHeight + 100) < 0)
				positionOfTooltipY = -1;
			
			Rectangle rectTooltip = new Rectangle(
						x + width/2 - maxWidth/2, 
						y + height/2 - (height/2 + maxHeight + 20) * positionOfTooltipY, 
						maxWidth + 20, 
						maxHeight
					);
			
			GuiUtils.renderBox(rectTooltip, new Color(40, 40, 40), new Color(20, 20, 20), 3, g);
			
			g.setColor(new Color(195, 197, 213));
			int yStr = 10;
			for (String line : this.tooltip) {
				g.drawString(line, x + width/2 - maxWidth/2 + 10, rectTooltip.getY() + yStr);
				yStr += 20;
			}
			g.setColor(old);
		}
	}
	
	public void onClick() throws SlickException {
		if (!this.disabled)
			SoundManager.getInstance().play(SoundType.Click, 0.2f);
	}
	
	public boolean isHoveringButton(int x, int y) {
		if (this.disabled)
			return false;
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
			this.y += futurState.equals(State.HOVERED) ? 2 : -2;
			this.state = futurState;
		}
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
		int width = Game.getInstance().getGc().getGraphics().getFont().getWidth(content) + 15;
		boolean changeWidth = false;
		
		if (width > this.width) {
			changeWidth = true;
		} else if (width < 200) {
			changeWidth = true;
			width = 200;
		}
		
		if (changeWidth) {
			this.x += this.width/2;
			this.width = width;
			this.x = x - this.width/2;
		}
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

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	public ArrayList<String> getTooltip() {
		return tooltip;
	}

	public void setTooltip(ArrayList<String> tooltip) {
		this.tooltip = tooltip;
	}
}
