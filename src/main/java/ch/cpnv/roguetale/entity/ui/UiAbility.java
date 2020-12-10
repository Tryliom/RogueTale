package ch.cpnv.roguetale.entity.ui;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;

import ch.cpnv.roguetale.entity.character.Ability;

public class UiAbility extends UiAction {
	protected Ability ability;
	protected Image buttonIcon;

	public UiAbility(int x, int y, Ability ability) {
		super(x, y);
		this.ability = ability;
		this.buttonIcon = ability.getButtonIcon().getScaledCopy((int) getButtonIconDimension(), (int) getButtonIconDimension());
	}
	
	public Ability getAbility() {
		return ability;
	}
	
	@Override
	protected Color getActionColor() {
		if(ability.getDurationPercent() > 0) {
			return new Color(0.5f, 0.9f, 0.5f, 0.9f);
		}
		return new Color(0.9f, BASE_COLOR_PART, BASE_COLOR_PART, 0.9f);
	}
	
	@Override
	protected Color getButtonColor() {
		return new Color(0.85f, 0.85f, 0.85f, 1);
	}

	@Override
	protected float getActionRectangleWidthFactor() {
		if(ability.getDurationPercent() > 0) {
			return ability.getDurationPercent();
		}
		return ability.getCooldownPercent();
	}
	
	@Override 
	protected void drawIcon() {
		Rectangle iconRectangle = getIconRectangle();
		if (ability != null) {
			ability.getIcon().getScaledCopy(DIMENSION_ICON, DIMENSION_ICON).draw(iconRectangle.getX(), iconRectangle.getY());
		}
	}
	
	@Override
	protected void drawButton(Graphics g) {
		drawButtonRectangle(g);
		Rectangle buttonRectangle = getButtonRectangle();
		buttonIcon.draw(
				buttonRectangle.getCenterX() - buttonIcon.getWidth() / 2, 
				buttonRectangle.getCenterY() - buttonIcon.getHeight() / 2
		);
	}
	
	@Override
	protected float getButtonIconDimension() {
		return super.getButtonIconDimension() * 0.9f;
	}
}
