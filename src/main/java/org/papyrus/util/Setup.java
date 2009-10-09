package org.papyrus.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;

public class Setup {
	private final Map<String, String[]> map;

	private final PropertiesLoader propertiesLoader;

	public Setup(PropertiesLoader propertiesLoader) {
		this.propertiesLoader = propertiesLoader;

		map = new HashMap<String, String[]>();

		map.put("base-config.properties", new String[] { "scheduler.delay", "scheduler.period" });

		map.put("base-database.properties", new String[] { "connection.driverClassName", "connection.url",
				"connection.dialect" });

		map.put("user-database.properties", new String[] { "connection.username", "connection.password" });

		map.put("mail.properties", new String[] { "mail.smtp.host", "mail.smtp.port", "mail.smtp.username",
				"mail.smtp.password", "mail.smtp.auth", "mail.smtp.starttls" });
	}

	public void createFiles() throws IOException {
		for (String key : map.keySet()) {
			createFile(key, map.get(key));
		}
	}

	private void createFile(String fileName, String[] keys) throws IOException {
		Properties properties = new Properties();
		for (String key : keys) {
			properties.setProperty(key, askValueForKey(key));
		}
		propertiesLoader.writePropertie(properties, fileName);
	}

	private String askValueForKey(String key) {
		Scanner scanner = new Scanner(System.in);
		System.out.println(key + ": ");
		return scanner.nextLine()
				.trim();
	}

	public static void main(String... args) throws IOException {
		new Setup(new PropertiesLoader("./webapps/papyrus/WEB-INF/classes")).createFiles();
	}
}
