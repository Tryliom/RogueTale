package ch.cpnv.roguetale.purchase;

import java.io.Serializable;

import ch.cpnv.roguetale.controller.MoneyController;

public class Purchase implements Serializable {
	private static final long serialVersionUID = -8024550476266968803L;
	protected String displayName;
	protected String description;
	protected int level;
	protected int levelMax;
	protected int[] costByLevel;
	
	public Purchase(String displayName, int levelMax, int[] costByLevel) {
		this.displayName = displayName;
		this.level = 0;
		this.levelMax = levelMax;
		this.costByLevel = costByLevel;
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


	public void initDescription() {
		// Do in subclasses
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


	public int[] getCostByLevel() {
		return costByLevel;
	}


	public void setCostByLevel(int[] costByLevel) {
		this.costByLevel = costByLevel;
	}

	public int getCurrentCost() {
		return this.costByLevel[this.level];
	}

	public boolean canBuy() {
		return this.level < this.levelMax && this.getCurrentCost() < MoneyController.getInstance().getMoney();
	}
	
	public void buy() {
		if (this.canBuy()) {
			MoneyController.getInstance().removeMoney(this.getCurrentCost());
			this.level++;
		}
	}
	
}
