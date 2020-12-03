package ch.cpnv.roguetale.entity.ui;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public abstract class UiAction {
	protected static final int DIMENSION_ICON = 48;
	protected static final int DIMENSION_BUTTON = 24;
	protected static final int WIDTH_RECTANGLE_ICON = 2;
	protected static final int DIMENSION = DIMENSION_ICON + WIDTH_RECTANGLE_ICON;
	
	protected static final float BASE_COLOR_PART = 0.4f;
	
	protected int x;
	protected int y;
	
	public UiAction(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void render(GameContainer gc, Graphics g, Vector2f origin) throws SlickException {		
		Color graphicsColor = g.getColor();
		
		// Fill box
		g.setColor(getBackgroundColor());
		g.fill(getIconRectangle());
		g.setColor(getActionColor());	
		g.fill(getActionRectangle());
		
		// Draw the borders
		drawBorders(g);
		
		// Draw the icon
		g.setColor(graphicsColor);
		drawIcon();
		
		// Draw button
		drawButton(g);
		
		// Reset the graphics
		g.setColor(graphicsColor);
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	protected Rectangle getIconRectangle() {
		return new Rectangle(x - DIMENSION/2, y - DIMENSION - 15, DIMENSION, DIMENSION);
	}
	
	protected Rectangle getButtonRectangle() {
		Rectangle iconRectangle = getIconRectangle();
		return new Rectangle(
				iconRectangle.getMaxX() - DIMENSION_BUTTON / 2, 
				iconRectangle.getMaxY() - DIMENSION_BUTTON / 2, 
				DIMENSION_BUTTON, 
				DIMENSION_BUTTON);
	}
	
	protected Rectangle getActionRectangle() {
		Rectangle iconRectangle = getIconRectangle();
		return new Rectangle(
				iconRectangle.getX(), 
				iconRectangle.getY(), 
				iconRectangle.getWidth() * getActionRectangleWidthFactor(), 
				iconRectangle.getHeight()
		);
	}
	
	protected float getActionRectangleWidthFactor() {
		// Implementation to be overridden if there is an action to be drawn
		return 0;
	}
	
	protected Color getBackgroundColor() {
		return new Color(BASE_COLOR_PART, BASE_COLOR_PART, BASE_COLOR_PART, 0.6f);
	}
	
	protected Color getActionColor() {
		return getBackgroundColor();
	}
	
	protected void drawBorders(Graphics g) {
		g.setColor(Color.white);
		g.setLineWidth(WIDTH_RECTANGLE_ICON);
		g.draw(getIconRectangle());
		g.resetLineWidth();
	}
	
	protected void drawIcon() {
		// Implementation to be overridden if there is an icon to draw
	}
	
	protected void drawButton(Graphics g) {
		// Implementation to be overridden if there is a button to draw
	}
	
	protected void drawButtonRectangle(Graphics g) {
		g.setColor(Color.darkGray);
		g.fill(getButtonRectangle());
	}
}
