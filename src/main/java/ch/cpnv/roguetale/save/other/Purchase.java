package ch.cpnv.roguetale.save.other;

import org.newdawn.slick.Image;

public class Purchase {
	private String displayName;
	private String description;
	private int level;
	private int levelMax;
	private Image icon;
	private int cost;
	private int exponentialCost;
	
	public Purchase(String displayName, String description, int level, int levelMax, Image icon, int cost, int exponentialCost) {
		this.displayName = displayName;
		this.description = description;
		this.level = level;
		this.levelMax = levelMax;
		this.icon = icon;
		this.cost = cost;
		this.exponentialCost = exponentialCost;
	}
	
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getLevelMax() {
		return levelMax;
	}
	public void setLevelMax(int levelMax) {
		this.levelMax = levelMax;
	}
	public Image getIcon() {
		return icon;
	}
	public void setIcon(Image icon) {
		this.icon = icon;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}

	public int getExponentialCost() {
		return exponentialCost;
	}

	public void setExponentialCost(int exponentialCost) {
		this.exponentialCost = exponentialCost;
	}
	
	
	
}
