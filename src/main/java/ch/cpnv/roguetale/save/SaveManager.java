package ch.cpnv.roguetale.save;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import ch.cpnv.roguetale.main.Main;

public class SaveManager {
	
	private String getAppPath() {
		return this.getDefaultPath() + File.separator + "roguetale";
	}
	
	private String getDefaultPath() {
		String OS = System.getProperty("os.name").toLowerCase();
		
		if (this.isWindows(OS)) {
			return System.getenv("APPDATA");
		} else if (this.isMac(OS)) {
			return System.getProperty("user.home") + File.separator + "Library" + File.separator + "Application Support";
		} else if (this.isUnix(OS)) {
			return "/usr/share";
		}
		
		return "/";
	}
	
	private void createSaveDirIfNotExist() {
		File dir = new File(this.getAppPath());
		if (!dir.exists())
			dir.mkdir();
	}
	
	private void createFileIfNotExist(File file) throws IOException {
		if (!file.exists())
			file.createNewFile();
	}
	
	private void saveContentToFile(String filename, Serializable data) throws IOException {
		this.createSaveDirIfNotExist();
		File file = new File(this.getAppPath() + File.separator + filename + ".config");
		FileOutputStream fos = new FileOutputStream(file.getPath());
		this.createFileIfNotExist(file);
		
		ObjectOutputStream obj = new ObjectOutputStream(fos);
		obj.writeObject(data);
		fos.close();
		obj.close();
		
	}
	
	private Serializable getContentFromFile(String filename) throws IOException, ClassNotFoundException {
		this.createSaveDirIfNotExist();
		File file = new File(this.getAppPath() + File.separator + filename + ".config");
		
		if (file.exists()) {
			FileInputStream fin = new FileInputStream(file.getPath());
			ObjectInputStream ois = new ObjectInputStream(fin);
			Serializable data = (Serializable) ois.readObject();
			ois.close();
			fin.close();
			return data;
		}
		
		return null;
	}
	
	public void saveSound() throws IOException {
		this.saveContentToFile("sound", Main.saveController.getSound());
	}
	
	public void loadSound() throws ClassNotFoundException, IOException {
		SaveSound data = (SaveSound) this.getContentFromFile("sound");

		if (data == null) {
			data = new SaveSound();
			data.setDefaultData();
		}
		
		Main.saveController.setSound(data);
	}
	
	public void saveGraphic() throws IOException {
		this.saveContentToFile("graphic", Main.saveController.getGraphic());
	}
	
	public void loadGraphic() throws ClassNotFoundException, IOException {
		SaveGraphic data = (SaveGraphic) this.getContentFromFile("graphic");

		if (data == null) {
			data = new SaveGraphic();
			data.setDefaultData();
		}
		
		Main.saveController.setGraphic(data);
	}
	
	public void saveCommand() throws IOException {
		this.saveContentToFile("command", Main.saveController.getCommand());
	}
	
	public void loadCommand() throws ClassNotFoundException, IOException {
		SaveCommand data = (SaveCommand) this.getContentFromFile("command");

		if (data == null) {
			data = new SaveCommand();
			data.setDefaultData();
		}
		
		Main.saveController.setCommand(data);
	}
	
	public void savePurchase() throws IOException {
		this.saveContentToFile("purchase", Main.saveController.getPurchase());
	}
	
	public void loadPurchase() throws ClassNotFoundException, IOException {
		SavePurchase data = (SavePurchase) this.getContentFromFile("purchase");

		if (data == null) {
			data = new SavePurchase();
		}
		
		Main.saveController.setPurchase(data);
	}
	
	public void saveProgress() throws IOException {
		this.saveContentToFile("progress", Main.saveController.getProgress());
	}
	
	public void loadProgress() throws ClassNotFoundException, IOException {
		SaveProgress data = (SaveProgress) this.getContentFromFile("progress");

		if (data == null) {
			data = new SaveProgress();
			data.setDefaultData();
		}
		
		Main.saveController.setProgress(data);
	}
	
	private boolean isWindows(String OS) {
		return (OS.indexOf("win") >= 0);
	}

	private boolean isMac(String OS) {
		return (OS.indexOf("mac") >= 0);
	}

	private boolean isUnix(String OS) {
		return (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0 || OS.indexOf("sunos") >= 0);
	}
}
