/**
 * 
 */
package org.papyrus.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

/**
 * Entity that represents a Category
 * 
 * @author davidpaniz
 */
@Entity
@SequenceGenerator(name = "Category_Seq")
public class Category {
	@Id
	@GeneratedValue(generator = "Category_Seq", strategy = GenerationType.AUTO)
	private long id;
	private String name;

	@ManyToOne
	private Category parent;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setParent(Category parent) {
		this.parent = parent;
	}

	public Category getParent() {
		return parent;
	}

}
