package ch.cpnv.roguetale.save;

import java.io.Serializable;

import org.lwjgl.util.vector.Vector2f;

import ch.cpnv.roguetale.main.Main;

public class SaveData implements Serializable {
	private static final long serialVersionUID = 2310369966017704006L;
	private Vector2f resolution;
	private Boolean fullscreen;

	public void setDefaultData() {
		this.resolution = new Vector2f(Main.BASE_WIDTH, Main.BASE_HEIGHT);
		this.fullscreen = Main.app.isFullscreen();
	}

	public void setResolution(Vector2f resolution) {
		this.resolution = resolution;
	}

	public void setFullscreen(Boolean fullscreen) {
		this.fullscreen = fullscreen;
	}

	public Vector2f getResolution() {
		return resolution;
	}

	public Boolean getFullscreen() {
		return fullscreen;
	}
}
