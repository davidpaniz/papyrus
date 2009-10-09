package org.papyrus.util;

import java.io.IOException;
import java.util.Properties;

import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.dialect.Dialect;
import org.papyrus.domain.model.Action;
import org.papyrus.domain.model.Attachment;
import org.papyrus.domain.model.BusinessRule;
import org.papyrus.domain.model.Category;
import org.papyrus.domain.model.Client;
import org.papyrus.domain.model.Company;
import org.papyrus.domain.model.Condition;
import org.papyrus.domain.model.Detail;
import org.papyrus.domain.model.Impact;
import org.papyrus.domain.model.Incident;
import org.papyrus.domain.model.MailNotification;
import org.papyrus.domain.model.Priority;
import org.papyrus.domain.model.Staff;
import org.papyrus.domain.model.Task;
import org.papyrus.domain.model.TemplateValue;
import org.papyrus.domain.model.Urgency;
import org.papyrus.domain.model.User;
import org.papyrus.domain.model.WorkOrder;

public class HibernateSchemaExport {
	public static void main(String[] args) throws IOException {
		AnnotationConfiguration config = new AnnotationConfiguration();

		config.addAnnotatedClass(User.class);
		config.addAnnotatedClass(Urgency.class);
		config.addAnnotatedClass(Impact.class);
		config.addAnnotatedClass(Priority.class);
		config.addAnnotatedClass(Incident.class);
		config.addAnnotatedClass(WorkOrder.class);
		config.addAnnotatedClass(Attachment.class);
		config.addAnnotatedClass(BusinessRule.class);
		config.addAnnotatedClass(Condition.class);
		config.addAnnotatedClass(Action.class);
		config.addAnnotatedClass(TemplateValue.class);
		config.addAnnotatedClass(MailNotification.class);
		config.addAnnotatedClass(Task.class);
		config.addAnnotatedClass(Company.class);
		config.addAnnotatedClass(Client.class);
		config.addAnnotatedClass(Category.class);
		config.addAnnotatedClass(Staff.class);
		config.addAnnotatedClass(Detail.class);

		Properties props = new PropertiesLoader().loadProperties();

		props.setProperty("hibernate.dialect", props.getProperty("connection.dialect"));

		// String[] generateDropSchemaScript = config.generateDropSchemaScript(Dialect.getDialect(props));
		// for (String string : generateDropSchemaScript) {
		// System.out.println(string + ";");
		// }
		String[] generateSchemaCreationScript = config.generateSchemaCreationScript(Dialect.getDialect(props));
		for (String string : generateSchemaCreationScript) {
			System.out.println(string + ";");
		}
	}
}
