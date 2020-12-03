package ch.cpnv.roguetale.entity.ui;

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
	protected float getActionRectangleWidthFactor() {
		return ability.getCooldownPercent();
	}
}
