package ch.cpnv.roguetale.entity.ui;

import org.newdawn.slick.Color;
import ch.cpnv.roguetale.entity.character.Ability;

public class UiAbility extends UiAction {
	protected Ability ability;

	public UiAbility(int x, int y, Ability ability) {
		super(x, y);
		this.ability = ability;
		setIcon(ability.getIcon());
		setButtonIcon(ability.getButtonIcon());
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
	protected float getButtonIconDimension() {
		return super.getButtonIconDimension() * 0.9f;
	}
}
