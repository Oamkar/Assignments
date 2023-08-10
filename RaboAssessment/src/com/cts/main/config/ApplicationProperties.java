package com.cts.main.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ApplicationProperties {

	private static Properties properties = new Properties();
	
	private static void loadApplicationProperties() {
		try {
		    FileInputStream fileInputStream = new FileInputStream("properties/application.properties");
		    properties.load(fileInputStream);
		    fileInputStream.close();
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}
	
	public static String getProperty(String props) {
		if(properties.isEmpty()) {
			loadApplicationProperties();
		}
		return properties.getProperty(props);
	}
}
