package org.papyrus.infra.hibernate;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.papyrus.domain.model.ConditionComparable;
import org.papyrus.domain.model.Task;
import org.papyrus.domain.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

@SuppressWarnings("unchecked")
@Repository(value = "taskRepository")
public class TaskDao implements TaskRepository {

	private final HibernateTemplate template;

	@Autowired
	public TaskDao(@Qualifier("hibernateTemplate") HibernateTemplate template) {
		this.template = template;
	}

	public Task saveTask(Task task) {
		template.save(task);
		return task;
	}

	public List<Task> taskToExecute() {
		String sql = "from Task t where t.duoDate <= current_timestamp() and t.executed = false";
		return template.find(sql);
	}

	public void update(Task task) {
		template.update(task);
	}

	public List<Task> tasksOf(ConditionComparable conditionComparable) {
		Criteria criteria = template.getSessionFactory()
				.getCurrentSession()
				.createCriteria(Task.class);
		criteria.add(Restrictions.eq("detail", conditionComparable));
		return criteria.list();
	}
}
