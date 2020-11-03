package ch.cpnv.roguetale.save;

import java.io.IOException;

public class SaveController {
	private SaveGraphic graphic;
	private SaveSound sound;
	
	public SaveController() {
		this.graphic = new SaveGraphic();
		this.sound = new SaveSound();
	}
	
	public void loadAll() throws ClassNotFoundException, IOException {
		new SaveManager().loadGraphic();
		new SaveManager().loadSound();
	}
	
	public void saveAll() throws IOException {
		new SaveManager().saveGraphic();
		new SaveManager().saveSound();
	}

	public SaveGraphic getGraphic() {
		return graphic;
	}

	public SaveSound getSound() {
		return sound;
	}

	public void setGraphic(SaveGraphic graphic) {
		this.graphic = graphic;
	}

	public void setSound(SaveSound sound) {
		this.sound = sound;
	}
}
