package org.papyrus.infra.hibernate;

import java.util.List;

import org.papyrus.domain.model.Staff;
import org.papyrus.domain.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

@SuppressWarnings("unchecked")
@Repository(value = "staffRepository")
public class StaffDao implements StaffRepository {

	private final HibernateTemplate template;

	@Autowired
	public StaffDao(@Qualifier("hibernateTemplate") HibernateTemplate template) {
		this.template = template;
	}

	public Staff saveOrUpdate(Staff staff) {
		template.saveOrUpdate(staff);
		return staff;
	}

	public List<Staff> list() {
		List list = template.find("from Staff");
		return list;

	}

	public Staff findById(Integer id) {
		return (Staff) template.load(Staff.class, id);
	}

	public Staff delete(Staff staff) {
		template.delete(staff);
		return staff;
	}

}
