package ch.cpnv.roguetale.entity.ui;

import org.newdawn.slick.geom.Rectangle;

import ch.cpnv.roguetale.entity.character.Ability;

public class UiAbility extends UiElement {
	protected Ability ability;

	public UiAbility(int x, int y, String imagePath, Ability ability) {
		super(x, y, imagePath);
		this.ability = ability;
	}
	
	public Ability getAbility() {
		return ability;
	}
	
	public void setAbility(Ability ability) {
		this.ability = ability;
	}

	@Override
	public Rectangle getActionRectangle() {
		Rectangle iconRectangle = getIconRectangle();
		// TODO add method to Ability
		float rectangleWidthFactor = (float) ability.getCurrentCooldown() / ability.getCooldown();
		return new Rectangle(
				iconRectangle.getX(), 
				iconRectangle.getY(), 
				iconRectangle.getWidth() * rectangleWidthFactor, 
				iconRectangle.getHeight()
		);
	}
}
