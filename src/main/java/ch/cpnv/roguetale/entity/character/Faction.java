package ch.cpnv.roguetale.entity.character;

import java.util.Random;
import java.util.UUID;

import org.newdawn.slick.Color;

public class Faction {
	private String id;
	private Color color;

	public Faction() {
		this.id = UUID.randomUUID().toString();
		float r = new Random().nextFloat();
		float g = new Random().nextFloat();
		float b = new Random().nextFloat();
		this.color = new Color(r, g, b);
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
}
