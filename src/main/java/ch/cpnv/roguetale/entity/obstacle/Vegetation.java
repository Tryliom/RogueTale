package ch.cpnv.roguetale.entity.obstacle;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import ch.cpnv.roguetale.damageable.HpDamage;

public class Vegetation extends Obstacle {
	static protected final String SPRITESHEET_PATH = "ch\\cpnv\\roguetale\\images\\obstacles\\vegetation.png";
	static protected ArrayList<VegetationInformation> vegetationInformations;
	
	protected int hitboxDimension;
	
	public Vegetation(Vector2f position) throws SlickException {
		super(new SpriteSheet(SPRITESHEET_PATH, 32, 32), position);
		if(vegetationInformations == null) {
			initializeVegetationInformations();
		}		
		VegetationInformation vegetationInformation = vegetationInformations.get(
				ThreadLocalRandom.current().nextInt(vegetationInformations.size()));
		damageStrategy = new HpDamage(vegetationInformation.getStartingHealth());
		image = spritesheet.getSprite(
				vegetationInformation.getSpritesheetX(), vegetationInformation.getSpritesheetY());
		image = image.getScaledCopy(
				vegetationInformation.getImageDimension(), vegetationInformation.getImageDimension());
		hitboxDimension = vegetationInformation.getHitboxDimension();
	}
	
	protected static void initializeVegetationInformations() {
		vegetationInformations = new ArrayList<>();
		vegetationInformations.add(
				new VegetationInformation(2, 0, 32, 16, 1));
		vegetationInformations.add(
				new VegetationInformation(4, 2, 32, 16, 3));
		vegetationInformations.add(
				new VegetationInformation(1, 0, 16, 8, 1));
	}
	
	@Override
	protected int getHitboxWidth() {
		return hitboxDimension;
	}
	
	@Override
	protected int getHitboxHeight() {
		return hitboxDimension;
	}
}
