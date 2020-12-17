package ch.cpnv.roguetale.entity.ui;

import org.newdawn.slick.Color;
import ch.cpnv.roguetale.weapon.Weapon;

public class UiWeaponSlot extends UiAction {
	protected Weapon weapon;

	public UiWeaponSlot(int x, int y, String buttonImagePath, Weapon weapon) {
		super(x, y);
		this.weapon = weapon;
		setIcon(weapon.getIcon());
		setButtonIcon(buttonImagePath);
	}
	
	public Weapon getWeapon() {
		return weapon;
	}

	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
		setIcon(weapon.getIcon());
	}
	
	@Override
	protected float getActionRectangleWidthFactor() {
		float rectangleWidthFactor;
		if(weapon.getMinChargeTime() > 0 && weapon.isAiming()) {
			if(weapon.getMinChargePercentCompletion() < 1) {
				rectangleWidthFactor = weapon.getMinChargePercentCompletion();
			} else {
				rectangleWidthFactor = weapon.getMaxChargePercentCompletion();
			}
		}
		else {
			rectangleWidthFactor = weapon.getCooldownPercent();
		}
		return rectangleWidthFactor;
	}
	
	@Override
	protected Color getBackgroundColor() {
		if(weapon.getMinChargeTime() > 0 && weapon.isAiming()
				&& weapon.getMinChargePercentCompletion() >= 1) {
			return new Color(0.5f, 0.9f, 0.5f, 0.9f);
		}
		return super.getBackgroundColor();
	}
	
	@Override
	protected Color getActionColor() {
		if(weapon.getMinChargeTime() > 0 && weapon.isAiming()) {
			if(weapon.getMinChargePercentCompletion() < 1) {
				return new Color(0.5f, 0.9f, 0.5f, 0.9f);
			}
			return new Color(1f, 0.6f, 0.5f, 0.9f);
		}
		return new Color(0.9f, BASE_COLOR_PART, BASE_COLOR_PART, 0.9f);
	}
}
