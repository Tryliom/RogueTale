package ch.cpnv.roguetale.entity.obstacle;

public class VegetationInformation {
	protected int spritesheetX;
	protected int spritesheetY;
	protected int imageDimension;
	protected int hitboxDimension;
	protected int startingHealth;
	
	public VegetationInformation(
			int spritesheetX, int spritesheetY,
			int imageDimension, int hitboxDimension, 
			int startingHealth) {
		this.spritesheetX = spritesheetX;
		this.spritesheetY = spritesheetY;
		this.imageDimension = imageDimension;
		this.hitboxDimension = hitboxDimension;
		this.startingHealth = startingHealth;
	}
	
	public int getSpritesheetX() {
		return spritesheetX;
	}

	public int getSpritesheetY() {
		return spritesheetY;
	}

	public int getImageDimension() {
		return imageDimension;
	}

	public int getHitboxDimension() {
		return hitboxDimension;
	}

	public int getStartingHealth() {
		return startingHealth;
	}
}
