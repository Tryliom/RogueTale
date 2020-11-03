package ch.cpnv.roguetale.save;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

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
	
	private void saveContentToFile(String filename, SaveGraphic data) throws IOException {
		this.createSaveDirIfNotExist();
		File file = new File(this.getAppPath() + File.separator + filename + ".config");
		FileOutputStream fos = new FileOutputStream(file.getPath());
		this.createFileIfNotExist(file);
		
		ObjectOutputStream obj = new ObjectOutputStream(fos);
		obj.writeObject(data);
		fos.close();
		obj.close();
		
	}
	
	private SaveGraphic getContentFromFile(String filename) throws IOException, ClassNotFoundException {
		this.createSaveDirIfNotExist();
		File file = new File(this.getAppPath() + File.separator + filename + ".config");
		
		if (file.exists()) {
			FileInputStream fin = new FileInputStream(file.getPath());
			ObjectInputStream ois = new ObjectInputStream(fin);
			SaveGraphic data = (SaveGraphic) ois.readObject();
			ois.close();
			fin.close();
			return data;
		}
		
		return null;
	}
	
	public void saveGraphic() throws IOException {
		this.saveContentToFile("graphic", Main.data);
	}
	
	public void loadGraphic() throws ClassNotFoundException, IOException {
		SaveGraphic data = this.getContentFromFile("graphic");

		if (data == null) {
			data = new SaveGraphic();
			data.setDefaultData();
		}
		
		Main.data = data;
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
