package ch.cpnv.roguetale.save;

import java.io.Serializable;

public class SaveProgress implements Serializable {
	private static final long serialVersionUID = -2769091833724242802L;
	
	private int money;
	
	public void setDefaultData() {
		this.money = 0;
	}
	
	public void setMoney(int money) {
		this.money = money;
	}
	
	public int getMoney() {
		return money;
	}
}
