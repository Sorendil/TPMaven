package fr.tp.rossi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import fr.tp.rossi.common.PersistentObjectImpl;

@Entity
@Table(name = "mbookmark")
public class MBookmark extends PersistentObjectImpl {

	private static final long serialVersionUID = 1L;
	
	@NotNull
	@Column(name = "name", unique = false, nullable = false)
	private String name;

	public MBookmark() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "MBookmark[" + getId() + "," + serialVersionUID
				+ "]";
	}
}
