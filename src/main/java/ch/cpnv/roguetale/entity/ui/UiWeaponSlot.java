package ch.cpnv.roguetale.entity.ui;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

import ch.cpnv.roguetale.weapon.RangedWeapon;
import ch.cpnv.roguetale.weapon.Weapon;

public class UiWeaponSlot {
	private static final int DIMENSION_ICON = 48;
	private static final int DIMENSION_MOUSE = 24;
	private static final int WIDTH_RECTANGLE_ICON = 2;
	private static final int DIMENSION = DIMENSION_ICON + WIDTH_RECTANGLE_ICON;
	private int x;
	private int y;
	private String path_image;
	private Weapon weapon;

	public UiWeaponSlot(int x, int y, String path_image, Weapon weapon) {
		this.x = x;
		this.y = y;
		this.path_image = path_image;
		this.weapon = weapon;
	}

	public void render(GameContainer gc, Graphics g, Vector2f origin) throws SlickException {
		Color old = g.getColor();
		Rectangle rect = new Rectangle(x - DIMENSION/2, y - DIMENSION - 15, DIMENSION, DIMENSION);
		// Fill box where the weapons are in cooldown
		float base = 0.4f;
		g.setColor(new Color(base, base, base, 0.6f));
		g.fill(rect);
		g.setColor(new Color(0.9f, base, base, 0.9f));	
		g.fill(new Rectangle(rect.getX(), rect.getY(), rect.getWidth() * ((float) weapon.getCurrentCooldown() / weapon.getCooldown()), rect.getHeight()));
		
		// Draw charge completion
		if (weapon.getMinChargeTime() > 0) {
			
			if (weapon.isAiming()) {
				
				g.setColor(new Color(0.5f, 0.9f, 0.5f, 0.9f));
				g.fill(new Rectangle(rect.getX(), rect.getY(), rect.getWidth() * weapon.getMinChargePercentCompletion(), rect.getHeight()));
				
				g.setColor(new Color(1f, 0.6f, 0f, 0.9f));
				g.fill(new Rectangle(rect.getX(), rect.getY(), rect.getWidth() * weapon.getMaxChargePercentCompletion(), rect.getHeight()));
			}
		}
		
		// Draw border on the weapons
		g.setColor(Color.white);
		g.setLineWidth(WIDTH_RECTANGLE_ICON);
		g.draw(rect);
		
		g.resetLineWidth();
		g.setColor(old);
		
		// Draw the weapons
		if (weapon != null) {
			weapon.getIcon().getScaledCopy(DIMENSION_ICON, DIMENSION_ICON).draw(rect.getX(), rect.getY());
		}

		// Draw button for the weapons
		g.setColor(Color.darkGray);
		g.fill(new Rectangle(rect.getMaxX()-DIMENSION_MOUSE/2, rect.getMaxY()-DIMENSION_MOUSE/2, DIMENSION_MOUSE, DIMENSION_MOUSE));
		new Image(path_image).getScaledCopy(DIMENSION_MOUSE, DIMENSION_MOUSE).draw(rect.getMaxX() - DIMENSION_MOUSE/2, rect.getMaxY() - DIMENSION_MOUSE/2);
		
		g.setColor(old);
	}

	public Weapon getWeapon() {
		return weapon;
	}

	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}
}
