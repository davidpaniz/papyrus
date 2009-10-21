package org.papyrus.util;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.papyrus.util.view.CheckInput;
import org.papyrus.util.view.DatabaseComboInput;
import org.papyrus.util.view.DatabaseComboOption;
import org.papyrus.util.view.Input;
import org.papyrus.util.view.MySqlComboOption;
import org.papyrus.util.view.PostgreComboOption;
import org.papyrus.util.view.TextInput;

public class Setup {
	private JFrame window;

	private JPanel mainPanel;

	private final Map<String, Input[]> map;

	private final PropertiesLoader propertiesLoader;

	public Setup(PropertiesLoader propertiesLoader) {
		this.propertiesLoader = propertiesLoader;

		map = new HashMap<String, Input[]>();

		map.put("base-config.properties", new Input[] { new TextInput("scheduler.delay", "Delay to start scheduler"),
				new TextInput("scheduler.period", "period os scheduler (in milisec)") });

		map.put("base-database.properties", new Input[] { new DatabaseComboInput(new DatabaseComboOption[] {
				new MySqlComboOption(), new PostgreComboOption() }) });

		map.put("user-database.properties", new Input[] { new TextInput("connection.username", "Database username"),
				new TextInput("connection.password", "Database password") });

		map.put("mail.properties", new Input[] { new TextInput("mail.smtp.host", "SMTP Host"),
				new TextInput("mail.smtp.port", "SMTP port"), new TextInput("mail.smtp.username", "SMTP user"),
				new TextInput("mail.smtp.password", "SMTP password"), new CheckInput("mail.smtp.auth", "SMTP auth"),
				new CheckInput("mail.smtp.starttls", "SMTP starttls") });
	}

	public void createFiles() throws IOException {
		for (String key : map.keySet()) {
			createFile(key, map.get(key));
		}
	}

	private void createFile(String fileName, Input[] inputs) throws IOException {
		Properties properties = new Properties();
		for (Input input : inputs) {
			properties.putAll(input.getValue());
		}
		propertiesLoader.writePropertie(properties, fileName);
	}

	public static void main(String... args) throws IOException {
		// new Setup(new PropertiesLoader("./webapps/papyrus/WEB-INF/classes")).createFiles();
		new Setup(new PropertiesLoader("./webapps/papyrus/WEB-INF/classes")).createScreen();

	}

	private void createScreen() {
		createWindow();
		createMainPanel();
		createForm();
		createFinishButton();
		showWindow();
	}

	private void createForm() {
		for (String string : this.map.keySet()) {
			Input[] inputs = map.get(string);
			for (Input input : inputs) {
				mainPanel.add(input.show());
			}
		}
	}

	private void showWindow() {
		window.pack();
		window.setSize(500, 450);
		window.setVisible(true);
	}

	private void createFinishButton() {
		JButton finishButton = new JButton("Finish");
		finishButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				try {
					createFiles();
					JOptionPane.showConfirmDialog(null, "Setup is done!", "Ok", -1);
					System.exit(0);
				} catch (Throwable e) {
					JOptionPane.showConfirmDialog(null, e.getMessage(), "Fail", -1);
				}
			}
		});
		mainPanel.add(finishButton);
	}

	private void createMainPanel() {
		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
		window.add(mainPanel);
	}

	private void createWindow() {
		window = new JFrame("Setup Papyrus");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
