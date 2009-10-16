package org.papyrus.util.view;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Panel;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public abstract class Input {

	private final String field;
	private final JLabel label;

	private final Panel panel;

	protected Input(String field, String label) {
		this.field = field;

		this.panel = new Panel();
		this.panel.setLayout(new GridLayout(1, 2));
		this.label = new JLabel(label);
		this.label.setHorizontalAlignment(SwingConstants.RIGHT);
		this.panel.add(this.label);
	}

	protected void addComponent(Component comp) {
		this.panel.add(comp);
	}

	public String getField() {
		return field;
	}

	public Component show() {
		return this.panel;
	}

	public abstract String getValue();
}
