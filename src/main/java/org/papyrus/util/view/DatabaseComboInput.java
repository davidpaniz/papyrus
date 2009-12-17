package org.papyrus.util.view;

import java.awt.GridLayout;
import java.util.Properties;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class DatabaseComboInput extends Input {
	private final JComboBox comboBox;
	private final TextInput hostInput = new TextInput("host", "Database Host");
	private final TextInput portInput = new TextInput("port", "Database Port");
	private final TextInput databaseInput = new TextInput("port", "Database name");

	public DatabaseComboInput(DatabaseComboOption[] items) {
		super(4, 1);
		comboBox = new JComboBox(items);
		JPanel jPanel = new JPanel();
		jPanel.setLayout(new GridLayout(1, 2));
		JLabel label = new JLabel("Database");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		jPanel.add(label);
		jPanel.add(this.comboBox);
		super.addComponent(jPanel);
		super.addComponent(this.hostInput.show());
		super.addComponent(this.portInput.show());
		super.addComponent(this.databaseInput.show());
	}

	@Override
	public Properties getValue() {
		DatabaseComboOption selectedItem = (DatabaseComboOption) this.comboBox.getSelectedItem();

		Properties properties = new Properties();
		properties.put("connection.driverClassName", selectedItem.getDriver());
		properties.put("connection.dialect", selectedItem.getDialect());
		properties.put("connection.url", selectedItem.getConnectionString(this.hostInput.getText(),
				this.portInput.getText(), this.databaseInput.getText()));
		properties.put("databaseName", selectedItem.getDatabaseName());
		return properties;
	}
}
