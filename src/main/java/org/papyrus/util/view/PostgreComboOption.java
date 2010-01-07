package org.papyrus.util.view;

public class PostgreComboOption extends DatabaseComboOption {

	public PostgreComboOption() {
		super("Postgre", "org.postgresql.Driver", "org.hibernate.dialect.PostgreSQLDialect");
	}

	@Override
	public String getConnectionString(String host, String port, String dataBase) {
		return String.format("jdbc:postgresql://%s:%s/%s", host, port, dataBase);
	}
}
