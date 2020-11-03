package ch.cpnv.roguetale.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
	protected final String propertiesFileName = "config.properties";
	protected InputStream inputStream;
	
	public Properties getProperties() throws IOException {
		Properties properties = new Properties();
		try {
			File propertiesFile = new File(propertiesFileName);
		    inputStream = new FileInputStream(propertiesFile);
 
			if (inputStream != null) {
				properties.load(inputStream);
			} else {
				throw new FileNotFoundException("Property file '" + propertiesFileName + "' not found");
			}
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
			inputStream.close();
		}
		return properties;
	}
}
