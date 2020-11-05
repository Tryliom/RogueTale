package ch.cpnv.roguetale.entity.character;

import java.util.UUID;

public class Faction {
	private String id;

	public Faction() {
		this.id = UUID.randomUUID().toString();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
