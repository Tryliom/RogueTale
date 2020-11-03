package ch.cpnv.roguetale.save;

import java.io.Serializable;

public class SaveSound implements Serializable {
	private static final long serialVersionUID = 901932422944243230L;
	private Integer sound;
	private Integer music;

	public void setDefaultData() {
		this.sound = 10;
		this.music = 10;
	}

	public Integer getSound() {
		return sound;
	}

	public void setSound(Integer sound) {
		this.sound = sound;
	}

	public Integer getMusic() {
		return music;
	}

	public void setMusic(Integer music) {
		this.music = music;
	}
}
