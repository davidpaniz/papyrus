package org.papyrus.util.view;

import java.util.Properties;

import javax.swing.JTextField;

public class TextInput extends Input {
	private final JTextField textField;
	private final String field;

	public TextInput(String field, String label) {
		super(label);
		this.field = field;
		textField = new JTextField();
		textField.setSize(200, 20);
		super.addComponent(this.textField);
	}

	@Override
	public Properties getValue() {
		Properties properties = new Properties();
		properties.put(this.field, String.valueOf(this.textField.getText()));
		return properties;
	}

	public String getText() {
		return this.textField.getText();
	}
}
