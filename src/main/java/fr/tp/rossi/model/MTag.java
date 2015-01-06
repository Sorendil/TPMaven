package fr.tp.rossi.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.codehaus.jackson.annotate.JsonBackReference;

import fr.tp.rossi.common.PersistentObjectImpl;

@Entity
@Table(name = "MTag", uniqueConstraints={@UniqueConstraint(columnNames={"name"})})
public class MTag extends PersistentObjectImpl {

	private static final long serialVersionUID = 1L;
	
	@NotNull
	@Column(name = "name", unique = true, nullable = false)
	private String name;

	
	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "tags")
	private Set<MBookmark> bookmarks = new HashSet<MBookmark>(0);

	public MTag() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@JsonBackReference
	public Set<MBookmark> getBookmarks() {
		return this.bookmarks;
	}
 
	public void setBookmarks(Set<MBookmark> bookmarks) {
		this.bookmarks = bookmarks;
	}

	@Override
	public String toString() {
		return "MTag[" + getId() + "," + serialVersionUID
				+ "]";
	}

}
