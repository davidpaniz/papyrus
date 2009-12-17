package org.papyrus.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * Runs a batch of sql statements
 */
public class JDBCLoader {
	// private final Logger logger = Logger.getLogger(JDBCLoader.class);
	private final Connection connection;
	private final PropertiesLoader propertiesLoader;
	private final Properties properties;

	public JDBCLoader(PropertiesLoader propertiesLoader) {
		this.propertiesLoader = propertiesLoader;
		Connection connection = null;
		try {
			properties = propertiesLoader.loadProperties();
			Class.forName(properties.getProperty("connection.driverClassName"));
			connection = DriverManager.getConnection(properties.getProperty("connection.url"),
					properties.getProperty("connection.username"), properties.getProperty("connection.password"));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		this.connection = connection;
	}

	/**
	 * The sql file to load, relative to the classpath
	 * 
	 * @param sqlfile
	 */
	public void run(String sqlfile) {
		// System.out.println("==============================" + sqlfile + "==============================");
		BufferedReader reader = null;
		FileReader fileReader = null;

		try {
			fileReader = new FileReader(sqlfile);
			reader = new BufferedReader(fileReader);

			String line = null;

			while ((line = reader.readLine()) != null) {
				if (line.trim()
						.length() > 0) {
					// logger.debug("JDBCLoader: [Running] " + line);
					this.runStatement(line);
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (fileReader != null) {
				try {
					fileReader.close();
				} catch (Exception e) {
				}
			}

			if (reader != null) {
				try {
					reader.close();
				} catch (Exception e) {
				}
			}
		}
	}

	private void runStatement(String sql) throws SQLException {
		Statement s = this.connection.createStatement();
		s.executeUpdate(sql);
		s.close();
	}

	public static void main(String... args) throws IOException, SQLException {
		PropertiesLoader propertiesLoader = new PropertiesLoader("WebContent/WEB-INF/classes");

		JDBCLoader jdbcLoader = new JDBCLoader(propertiesLoader);
		jdbcLoader.loadSchema();
		jdbcLoader.loadBootstrap();
	}

	public void loadSchema() {
		run(getRootPath() + String.format("/db_script/schema_%s.sql", this.properties.get("databaseName")));
	}

	public void loadBootstrap() {
		run(getRootPath() + String.format("/db_script/bootstrap_%s.sql", this.properties.get("databaseName")));
	}

	private String getRootPath() {
		return this.propertiesLoader.getParentFile();
	}
}
