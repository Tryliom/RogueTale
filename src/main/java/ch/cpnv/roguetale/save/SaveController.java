package ch.cpnv.roguetale.save;

public class SaveController {
	private SaveGraphic graphic;
	private SaveSound sound;
	
	public SaveController() {
		this.graphic = new SaveGraphic();
		this.sound = new SaveSound();
	}

	public SaveGraphic getGraphic() {
		return graphic;
	}

	public SaveSound getSound() {
		return sound;
	}
}
