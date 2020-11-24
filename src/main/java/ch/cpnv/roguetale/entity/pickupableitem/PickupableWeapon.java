package ch.cpnv.roguetale.entity.pickupableitem;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.SlickException;

import ch.cpnv.roguetale.controller.GuiController;
import ch.cpnv.roguetale.entity.character.Player;
import ch.cpnv.roguetale.gui.guis.ChangeWeaponGui;
import ch.cpnv.roguetale.weapon.Weapon;

public class PickupableWeapon extends PickupableItem {
	protected final static int IMAGE_WIDTH = 32;
	
	protected Weapon weapon;
	
	public PickupableWeapon(Weapon weapon, Vector2f position) {
		super(weapon.getIcon(), position);
		image = image.getScaledCopy(IMAGE_WIDTH, IMAGE_WIDTH);
		this.weapon = weapon;
	}

	@Override
	public void pickup(Player player) throws SlickException {		
		super.pickup(player);
		GuiController.getInstance().setDisplayGui(
				new ChangeWeaponGui(GuiController.getInstance().getDisplayGui(), 
						weapon));
	}
	
	public Weapon getWeapon() {
		return weapon;
	}
}
