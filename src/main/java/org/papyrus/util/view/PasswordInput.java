package org.papyrus.util.view;

import java.util.Properties;

import javax.swing.JPasswordField;

public class PasswordInput extends Input {
	private final JPasswordField textField;
	private final String field;

	public PasswordInput(String field, String label) {
		super(label);
		this.field = field;
		textField = new JPasswordField();
		textField.setSize(200, 20);
		super.addComponent(this.textField);
	}

	@Override
	public Properties getValue() {
		Properties properties = new Properties();
		properties.put(this.field, String.valueOf(this.textField.getPassword()));
		return properties;
	}

	public String getText() {
		return new String(this.textField.getPassword());
	}
}
