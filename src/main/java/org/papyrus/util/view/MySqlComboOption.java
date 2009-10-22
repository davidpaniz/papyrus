package org.papyrus.util.view;

public class MySqlComboOption extends DatabaseComboOption {

	public MySqlComboOption() {
		super("MySql", "com.mysql.jdbc.Driver", "org.hibernate.dialect.MySQL5InnoDBDialect");
	}

	@Override
	public String getConnectionString(String host, String port, String dataBase) {
		return String.format("jdbc:mysql://%s:%s/%s", host, port, dataBase);
	}

}
