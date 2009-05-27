/**
 * 
 */
package org.papyrus.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

/**
 * Entity that represents a Urgency
 * 
 * @author davidpaniz
 */
@Entity
@SequenceGenerator(name = "Urgency_Seq")
public class Urgency {
	@Id
	@GeneratedValue(generator = "Urgency_Seq", strategy = GenerationType.AUTO)
	private long id;
	private String description;
	// @Column(nullable = false)
	private String name;

	// @Column(unique = true)
	// private int position;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	// public int getPosition() {
	// return position;
	// }
	//
	// public void setPosition(int position) {
	// this.position = position;
	// }
}
