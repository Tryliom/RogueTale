package ch.cpnv.roguetale.entity.ui;

import org.newdawn.slick.Color;
import org.newdawn.slick.geom.Rectangle;

import ch.cpnv.roguetale.entity.character.Ability;

public class UiAbility extends UiElement {
	protected Ability ability;

	public UiAbility(int x, int y, Ability ability) {
		super(x, y);
		this.ability = ability;
	}
	
	public Ability getAbility() {
		return ability;
	}
	
	public void setAbility(Ability ability) {
		this.ability = ability;
	}
	
	@Override
	protected Color getActionColor() {
		if(ability.getDurationPercent() > 0) {
			return new Color(0.5f, 0.9f, 0.5f, 0.9f); 
		}
		return new Color(0.9f, BASE_COLOR_PART, BASE_COLOR_PART, 0.9f);
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
}
