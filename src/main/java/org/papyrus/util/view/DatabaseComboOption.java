package org.papyrus.util.view;

public abstract class DatabaseComboOption {
	private final String display;
	private final String driver;
	private final String dialect;

	public DatabaseComboOption(String display, String driver, String dialect) {
		this.display = display;
		this.driver = driver;
		this.dialect = dialect;
	}

	public String getDriver() {
		return driver;
	}

	public String getDialect() {
		return dialect;
	}

	@Override
	public String toString() {
		return this.display;
	}

	public abstract String getConnectionString(String host, String port, String dataBase);

	public abstract String getDatabaseName();
}
