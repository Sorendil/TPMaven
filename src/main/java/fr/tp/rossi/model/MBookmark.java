package fr.tp.rossi.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import fr.tp.rossi.common.PersistentObjectImpl;

@Entity
@Table(name = "MBookmark")
public class MBookmark extends PersistentObjectImpl {

	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(name = "name", unique = false, nullable = false)
	private String name;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "MBookmark_MTag",
		joinColumns = { @JoinColumn(name = "bookmark_id", nullable = false, updatable = false) },
		inverseJoinColumns = {
			@JoinColumn(name = "tag_id", nullable = false, updatable = false) })
	private Set<MTag> tags = new HashSet<MTag>(0);

	@Column(name = "description", unique = false, nullable = true)
	private String description;

	public MBookmark() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<MTag> getTags() {
		return this.tags;
	}

	public void setTags(Set<MTag> tags) {
		this.tags = tags;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "MBookmark[" + getId() + "," + serialVersionUID + "]";
	}
}
