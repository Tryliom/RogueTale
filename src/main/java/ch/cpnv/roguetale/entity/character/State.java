package ch.cpnv.roguetale.entity.character;

public class State {
	private String name;
	private int duration;
	
	protected State(String name, int duration) {
		this.name = name;
		this.duration = duration;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	public void update(int delta) {
		if (!this.isExpired())
			this.duration -= delta;
	}
	
	public boolean isExpired() {
		return this.duration <= 0;
	}

}
