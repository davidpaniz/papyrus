package org.papyrus.util;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.hibernate.HibernateException;
import org.papyrus.domain.model.User;
import org.papyrus.util.view.CheckInput;
import org.papyrus.util.view.DatabaseComboInput;
import org.papyrus.util.view.DatabaseComboOption;
import org.papyrus.util.view.Input;
import org.papyrus.util.view.MySqlComboOption;
import org.papyrus.util.view.PasswordInput;
import org.papyrus.util.view.PostgreComboOption;
import org.papyrus.util.view.TextInput;

public class Setup {
	private JFrame window;

	private JPanel mainPanel;

	private final Map<String, Input[]> map;

	private final PropertiesLoader propertiesLoader;

	private HibernateUtils hibernateUtils;

	public Setup(PropertiesLoader propertiesLoader) {
		this.propertiesLoader = propertiesLoader;

		map = new HashMap<String, Input[]>();

		map.put("base-config.properties", new Input[] { new TextInput("scheduler.delay", "Delay to start scheduler"),
				new TextInput("scheduler.period", "period os scheduler (in milisec)") });

		map.put("base-database.properties", new Input[] { new DatabaseComboInput(new DatabaseComboOption[] {
				new MySqlComboOption(), new PostgreComboOption() }) });

		map.put("user-database.properties", new Input[] { new TextInput("connection.username", "Database username"),
				new PasswordInput("connection.password", "Database password") });

		map.put("mail.properties", new Input[] { new TextInput("mail.smtp.host", "SMTP Host"),
				new TextInput("mail.smtp.port", "SMTP port"), new TextInput("mail.smtp.username", "SMTP user"),
				new PasswordInput("mail.smtp.password", "SMTP password"),
				new CheckInput("mail.smtp.auth", "SMTP auth"), new CheckInput("mail.smtp.starttls", "SMTP starttls") });

		map.put("user", new Input[] { new TextInput("name", "Admin Name"), new TextInput("email", "Admin Email"),
				new PasswordInput("password", "Admin Password") });
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
		new Setup(new PropertiesLoader("./webapps/papyrus/WEB-INF/classes")).createScreen();
		// new Setup(new PropertiesLoader()).createScreen();

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
		window.setSize(500, 550);
		window.setVisible(true);
	}

	private void createFinishButton() {
		JButton finishButton = new JButton("Finish");
		finishButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				try {
					createFiles();
					hibernateUtils = new HibernateUtils(propertiesLoader);
					int response = JOptionPane.showConfirmDialog(null,
							"Setup is done! Do you want to create tables now?", "Ok", 0);
					if (response == JOptionPane.YES_OPTION) {
						createTables();
					}
					response = JOptionPane.showConfirmDialog(null,
							"Tables were created. Do you want to load bootstrap data?", "Ok", 0);

					loadBootstrap(response == JOptionPane.YES_OPTION);

					JOptionPane.showConfirmDialog(null, "Setup is done!", "Ok", -1);
					System.exit(0);
				} catch (Throwable e) {
					e.printStackTrace();
					JOptionPane.showConfirmDialog(null, e.getMessage(), "Fail", -1);
				}
			}
		});
		mainPanel.add(finishButton);
	}

	void loadBootstrap(boolean shouldLoadBootStrap) throws HibernateException, IOException {
		Properties property = propertiesLoader.loadProperty("user");
		String email = property.getProperty("email");
		String name = property.getProperty("name");
		String password = property.getProperty("password");

		User user = new User();
		user.setEmail(email);
		user.setName(name);
		user.setPassword(password);

		new Bootstrap(hibernateUtils).loadBootstrap(user, shouldLoadBootStrap);

		new File(propertiesLoader.getParentFile(), "user").delete();
	}

	void createTables() throws IOException {
		hibernateUtils.exportSchema();
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
