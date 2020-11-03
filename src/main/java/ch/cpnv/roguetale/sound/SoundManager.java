package ch.cpnv.roguetale.sound;

import java.util.HashMap;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

import ch.cpnv.roguetale.main.Main;

public class SoundManager {
	private static SoundManager instance;
	private HashMap<SoundType, Sound> sounds = new HashMap<>();
		
	private SoundManager() throws SlickException {
		for (SoundType type : SoundType.values()) {
			this.addNewSound(type);
		}
	}
	
	private void addNewSound(SoundType type) throws SlickException {
		this.sounds.put(type, new Sound(getPath(type.name().toLowerCase())));
	}

	public static SoundManager getInstance() throws SlickException {
		return instance == null ? instance = new SoundManager() : instance;
	}

	private String getPath(String name) {
		return "ch\\cpnv\\roguetale\\audio\\" + name + ".ogg";
	}
	
	public void loop(SoundType type) {
		Sound theme = getSoundByType(type);
		theme.loop(1f, Main.saveController.getSound().getMusic()/10f);
	}
	
	public void play(SoundType type) {
		Sound theme = getSoundByType(type);
		theme.play(1f, Main.saveController.getSound().getSound()/10f);
	}
	
	public void play(SoundType type, float volume) {
		Sound theme = getSoundByType(type);
		theme.play(1f, volume * Main.saveController.getSound().getSound()/10f);
	}
	
	public void stop(SoundType type) {
		Sound theme = getSoundByType(type);
		if (theme.playing())
			theme.stop();
	}
	
	private Sound getSoundByType(SoundType type) {
		return this.sounds.get(type);
	}
}
