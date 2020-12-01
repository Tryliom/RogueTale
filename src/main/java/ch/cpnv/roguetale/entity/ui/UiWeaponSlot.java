package ch.cpnv.roguetale.entity.ui;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

import ch.cpnv.roguetale.weapon.Weapon;

public class UiWeaponSlot extends UiElement {
	private Weapon weapon;

	public UiWeaponSlot(int x, int y, String path_image, Weapon weapon) {
		super(x, y, path_image);
		this.weapon = weapon;
	}
	
	public Weapon getWeapon() {
		return weapon;
	}

	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}
	
	@Override
	public Rectangle getActionRectangle() {
		Rectangle iconRectangle = getIconRectangle();
		float rectangleWidthFactor;
		if(weapon.getMinChargeTime() > 0 && weapon.isAiming()) {
			if(weapon.getMinChargePercentCompletion() < 1) {
				rectangleWidthFactor = weapon.getMinChargePercentCompletion();
			} else {
				rectangleWidthFactor = weapon.getMaxChargePercentCompletion();
			}
		}
		else {
			// TODO add method to weapon
			rectangleWidthFactor = (float) weapon.getCurrentCooldown() / weapon.getCooldown();
		}
		
		return new Rectangle(
				iconRectangle.getX(), 
				iconRectangle.getY(), 
				iconRectangle.getWidth() * rectangleWidthFactor, 
				iconRectangle.getHeight()
		);
	}
	
	@Override
	public Color getBackgroundColor() {
		if(weapon.getMinChargeTime() > 0 && weapon.isAiming()
				&& weapon.getMinChargePercentCompletion() >= 1) {
			return new Color(0.5f, 0.9f, 0.5f, 0.9f);
		}
		return super.getBackgroundColor();
	}
	
	@Override
	public Color getActionColor() {
		if(weapon.getMinChargeTime() > 0 && weapon.isAiming()) {
			if(weapon.getMinChargePercentCompletion() < 1) {
				return new Color(0.5f, 0.9f, 0.5f, 0.9f);
			}
			return new Color(1f, 0.6f, 0.5f, 0.9f);
		}
		return new Color(0.9f, BASE_COLOR_PART, BASE_COLOR_PART, 0.9f);
	}
	
	@Override 
	protected void drawIcon() {
		Rectangle iconRectangle = getIconRectangle();
		if (weapon != null) {
			weapon.getIcon().getScaledCopy(DIMENSION_ICON, DIMENSION_ICON).draw(iconRectangle.getX(), iconRectangle.getY());
		}
	}
	
	@Override 
	protected void drawButton(Graphics g) {
		Rectangle iconRectangle = getIconRectangle();
		g.setColor(Color.darkGray);
		g.fill(new Rectangle(
				iconRectangle.getMaxX()-DIMENSION_BUTTON/2, 
				iconRectangle.getMaxY()-DIMENSION_BUTTON/2, 
				DIMENSION_BUTTON, 
				DIMENSION_BUTTON)
		);
		try {
			new Image(imagePath).getScaledCopy(DIMENSION_BUTTON, DIMENSION_BUTTON).draw(
					iconRectangle.getMaxX() - DIMENSION_BUTTON/2, 
					iconRectangle.getMaxY() - DIMENSION_BUTTON/2);
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
}
