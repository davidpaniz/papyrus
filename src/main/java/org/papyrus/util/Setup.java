package org.papyrus.util;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.papyrus.view.Input;
import org.papyrus.view.TextInput;

public class Setup {
	private JFrame window;

	private JPanel mainPanel;

	private final Map<String, Input[]> map;

	private final PropertiesLoader propertiesLoader;

	public Setup(PropertiesLoader propertiesLoader) {
		this.propertiesLoader = propertiesLoader;

		map = new HashMap<String, Input[]>();

		map.put("base-config.properties", new Input[] { new TextInput("scheduler.delay", "Delay para executar robo"),
				new TextInput("scheduler.period", "Milisegundos entre cada execução do robo") });

		map.put("base-database.properties", new Input[] {
				new TextInput("connection.driverClassName", "Classes do driver"),
				new TextInput("connection.url", "url de conexão"), new TextInput("connection.dialect", "Dialect") });

		map.put("user-database.properties", new Input[] {
				new TextInput("connection.username", "Usuário do banco de dados"),
				new TextInput("connection.password", "senha do banco de dados") });

		map.put("mail.properties", new Input[] { new TextInput("mail.smtp.host", "Host de smtp"),
				new TextInput("mail.smtp.port", "Porta de smtp"),
				new TextInput("mail.smtp.username", "usuario de smtp"),
				new TextInput("mail.smtp.password", "senha de smtp"), new TextInput("mail.smtp.auth", "requer auth"),
				new TextInput("mail.smtp.starttls", "starttls") });
	}

	public void createFiles() throws IOException {
		for (String key : map.keySet()) {
			createFile(key, map.get(key));
		}
	}

	private void createFile(String fileName, Input[] inputs) throws IOException {
		Properties properties = new Properties();
		for (Input input : inputs) {
			properties.setProperty(input.getField(), input.getValue());
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
		window.setSize(500, 500);
		window.setVisible(true);
	}

	private void createFinishButton() {
		JButton finishButton = new JButton("Finish");
		finishButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				System.out.println("bla");
			}
		});
		mainPanel.add(finishButton);
	}

	private void createMainPanel() {
		mainPanel = new JPanel(new GridLayout(20, 1));
		window.add(mainPanel);
	}

	private void createWindow() {
		window = new JFrame("Setup Papyrus");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
