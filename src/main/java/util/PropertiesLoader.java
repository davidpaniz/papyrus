package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesLoader {
	private final String parentFile;

	public PropertiesLoader(String parentFile) {
		this.parentFile = parentFile;
	}

	public PropertiesLoader() {
		this("WebContent/WEB-INF/classes");
	}

	public Properties loadProperties() throws IOException {
		Properties props = new Properties();

		props.load(new FileInputStream(new File(parentFile, "base-database.properties")));
		props.load(new FileInputStream(new File(parentFile, "user-database.properties")));

		return props;
	}
}