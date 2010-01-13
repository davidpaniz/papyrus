package org.papyrus.util;

import java.io.IOException;
import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.papyrus.domain.model.Attachment;
import org.papyrus.domain.model.BusinessRule;
import org.papyrus.domain.model.Category;
import org.papyrus.domain.model.Company;
import org.papyrus.domain.model.Condition;
import org.papyrus.domain.model.Detail;
import org.papyrus.domain.model.Impact;
import org.papyrus.domain.model.Incident;
import org.papyrus.domain.model.MailNotification;
import org.papyrus.domain.model.Priority;
import org.papyrus.domain.model.Task;
import org.papyrus.domain.model.Urgency;
import org.papyrus.domain.model.User;
import org.papyrus.domain.model.action.Action;
import org.papyrus.domain.model.action.StatusAction;
import org.papyrus.domain.model.action.UserActionValues;
import org.papyrus.domain.model.action.NotifyUserAction;

public class HibernateUtils {
	PropertiesLoader propertiesLoader;
	private final AnnotationConfiguration configuration;

	public HibernateUtils(PropertiesLoader propertiesLoader) throws IOException {
		this.propertiesLoader = propertiesLoader;
		configuration = createConfiguration();
	}

	public void exportSchema() throws IOException {
		new SchemaExport(configuration).create(false, true);
	}

	public SessionFactory getSessionFactory() throws HibernateException, IOException {
		return configuration.buildSessionFactory();
	}

	private AnnotationConfiguration createConfiguration() throws IOException {
		Properties props = propertiesLoader.loadProperties();
		AnnotationConfiguration config = new AnnotationConfiguration();
		config.setProperty("hibernate.dialect", props.getProperty("connection.dialect"))
				.setProperty("hibernate.connection.driver_class", props.getProperty("connection.driverClassName"))
				.setProperty("hibernate.connection.username", props.getProperty("connection.username"))
				.setProperty("hibernate.connection.password", props.getProperty("connection.password"))
				.setProperty("hibernate.connection.url", props.getProperty("connection.url"))

				.addAnnotatedClass(Action.class)
				.addAnnotatedClass(StatusAction.class)
				.addAnnotatedClass(UserActionValues.class)
				.addAnnotatedClass(NotifyUserAction.class)
				.addAnnotatedClass(Attachment.class)
				.addAnnotatedClass(BusinessRule.class)
				.addAnnotatedClass(Category.class)
				.addAnnotatedClass(Company.class)
				.addAnnotatedClass(Condition.class)
				.addAnnotatedClass(Detail.class)
				.addAnnotatedClass(Impact.class)
				.addAnnotatedClass(Incident.class)
				.addAnnotatedClass(MailNotification.class)
				.addAnnotatedClass(Priority.class)
				.addAnnotatedClass(Task.class)
				.addAnnotatedClass(Urgency.class)
				.addAnnotatedClass(User.class);

		return config;
	}
}
