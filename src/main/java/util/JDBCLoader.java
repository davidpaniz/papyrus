package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * Runs a batch of sql statements
 */
public class JDBCLoader {
	private static final Logger logger = Logger.getLogger(JDBCLoader.class);
	private final Connection connection;

	public JDBCLoader(Connection connection) {
		this.connection = connection;
	}

	/**
	 * The sql file to load, relative to the classpath
	 * 
	 * @param sqlfile
	 */
	public void run(String sqlfile) {
		BufferedReader reader = null;
		FileReader fileReader = null;

		try {
			fileReader = new FileReader(this.getClass()
					.getResource(sqlfile)
					.getFile());
			reader = new BufferedReader(fileReader);

			String line = null;

			while ((line = reader.readLine()) != null) {
				if (line.trim()
						.length() > 0) {
					logger.debug("JDBCLoader: [Running] " + line);
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
		Properties props = new PropertiesLoader().loadProperties();
		Connection connection = DriverManager.getConnection(props.getProperty("connection.url"),
				props.getProperty("connection.username"), props.getProperty("connection.password"));

		new JDBCLoader(connection).run("/db_script/schema.sql");
		new JDBCLoader(connection).run("/db_script/bootstrap.sql");
	}
}
