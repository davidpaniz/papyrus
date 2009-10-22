package org.papyrus.util.view;

import java.util.Properties;

import javax.swing.JCheckBox;

public class CheckInput extends Input {
	private final JCheckBox checkBox;
	private final String field;

	public CheckInput(String field, String label) {
		super(label);
		this.field = field;
		checkBox = new JCheckBox();
		super.addComponent(this.checkBox);
	}

	@Override
	public Properties getValue() {
		Properties properties = new Properties();
		properties.put(field, String.valueOf(this.checkBox.isSelected()));
		return properties;
	}
}
