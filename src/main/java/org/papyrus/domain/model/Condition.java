package org.papyrus.domain.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name = "Condition_Seq")
public class Condition {

	@Id
	@GeneratedValue(generator = "Condition_Seq", strategy = GenerationType.AUTO)
	private Long id;

	private boolean onCreate;
	private boolean onUpdate;
	private boolean onDelete;
	@Enumerated(EnumType.STRING)
	private ConditionType type;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ConditionType getType() {
		return type;
	}

	public void setType(ConditionType type) {
		this.type = type;
	}

	public void setOnCreate(boolean onCreate) {
		this.onCreate = onCreate;
	}

	public boolean isOnCreate() {
		return onCreate;
	}

	public void setOnUpdate(boolean onUpdate) {
		this.onUpdate = onUpdate;
	}

	public boolean isOnUpdate() {
		return onUpdate;
	}

	public void setOnDelete(boolean onDelete) {
		this.onDelete = onDelete;
	}

	public boolean isOnDelete() {
		return onDelete;
	}
}
