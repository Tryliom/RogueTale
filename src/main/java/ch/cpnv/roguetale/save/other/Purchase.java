package ch.cpnv.roguetale.save.other;

import ch.cpnv.roguetale.controller.MoneyController;
import ch.cpnv.roguetale.save.enums.PurchaseType;

public class Purchase {
	private PurchaseType name;
	private String description;
	private int level;
	private int levelMax;
	private int cost;
	/**
	 * Cost exponential, minimum 1. Calcultation of the next cost: cost *= exponentialCost
	 */
	private float exponentialCost;
	
	public Purchase(PurchaseType displayName, String description, int level, int levelMax, int cost, float exponentialCost) {
		this.name = displayName;
		this.description = description;
		this.level = level;
		this.levelMax = levelMax;
		this.cost = cost;
		this.exponentialCost = exponentialCost;
	}
	
	public PurchaseType getName() {
		return name;
	}
	public void setName(PurchaseType displayName) {
		this.name = displayName;
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
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}

	public float getExponentialCost() {
		return exponentialCost;
	}

	public void setExponentialCost(float exponentialCost) {
		this.exponentialCost = exponentialCost;
	}
	
	public boolean canBuy() {
		return this.level < this.levelMax && this.cost < MoneyController.getInstance().getMoney();
	}
	
	public void buy() {
		if (this.canBuy()) {
			MoneyController.getInstance().removeMoney(this.cost);
			this.level++;
			this.cost *= this.exponentialCost;
		}
	}
	
}
