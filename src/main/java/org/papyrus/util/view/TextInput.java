package org.papyrus.util.view;

import javax.swing.JTextField;

public class TextInput extends Input {
	private final JTextField textField;

	public TextInput(String field, String label) {
		super(field, label);
		textField = new JTextField();
		super.addComponent(this.textField);
	}

	@Override
	public String getValue() {
		return this.textField.getText();
	}
}
