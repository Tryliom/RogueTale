package ch.cpnv.roguetale.entity.ui;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public abstract class UiAction {
	private static final int DIMENSION_ICON = 48;
	private static final int DIMENSION_BUTTON = 24;
	
	protected static final int WIDTH_RECTANGLE_ICON = 2;
	protected static final int DIMENSION = DIMENSION_ICON + WIDTH_RECTANGLE_ICON;
	
	protected static final float BASE_COLOR_PART = 0.4f;
	
	protected int x;
	protected int y;
	
	protected Image icon;
	protected Image buttonIcon;
	
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
	
	protected void setIcon(Image image) {
		this.icon = image.getScaledCopy(
				(int) getIconDimension(), 
				(int) getIconDimension()
		);
	}
	
	protected void setButtonIcon(Image image) {
		this.buttonIcon = image.getScaledCopy(
				(int) getButtonIconDimension(), 
				(int) getButtonIconDimension()
		);
	}
	
	protected void setButtonIcon(String imagePath) {
		try {
			setButtonIcon(new Image(imagePath));
		} catch (SlickException e) {
			e.printStackTrace();
		}
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
	
	protected float getIconDimension() {
		return DIMENSION_ICON;
	}
	
	protected float getButtonIconDimension() {
		return DIMENSION_BUTTON;
	}
	
	protected Color getBackgroundColor() {
		return new Color(BASE_COLOR_PART, BASE_COLOR_PART, BASE_COLOR_PART, 0.6f);
	}
	
	protected Color getActionColor() {
		return getBackgroundColor();
	}
	
	protected Color getButtonColor() {
		return Color.darkGray;
	}
	
	protected void drawBorders(Graphics g) {
		g.setColor(Color.white);
		g.setLineWidth(WIDTH_RECTANGLE_ICON);
		g.draw(getIconRectangle());
		g.resetLineWidth();
	}
	
	protected void drawIcon() {
		if(icon != null) {
			Rectangle iconRectangle = getIconRectangle();
			icon.draw(iconRectangle.getX(), iconRectangle.getY());
		}
	}
	
	protected void drawButton(Graphics g) {
		if(buttonIcon != null) {
			drawButtonRectangle(g);
			Rectangle buttonRectangle = getButtonRectangle();
			buttonIcon.draw(
					buttonRectangle.getCenterX() - buttonIcon.getWidth() / 2, 
					buttonRectangle.getCenterY() - buttonIcon.getHeight() / 2
			);	
		}
	}
	
	protected void drawButtonRectangle(Graphics g) {
		g.setColor(getButtonColor());
		g.fill(getButtonRectangle());
	}
	
	
}
