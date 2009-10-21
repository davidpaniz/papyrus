package org.papyrus.util.view;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Panel;
import java.util.Properties;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public abstract class Input {

	private JLabel label;

	private final Panel panel;

	protected Input(String label) {
		this(1, 2);
		this.label = new JLabel(label);
		this.label.setHorizontalAlignment(SwingConstants.RIGHT);
		this.panel.add(this.label);
	}

	protected Input(int rows, int cols) {
		this.panel = new Panel();
		this.panel.setLayout(new GridLayout(rows, cols));
	}

	protected void addComponent(Component comp) {
		this.panel.add(comp);
	}

	public Component show() {
		return this.panel;
	}

	public abstract Properties getValue();
}
