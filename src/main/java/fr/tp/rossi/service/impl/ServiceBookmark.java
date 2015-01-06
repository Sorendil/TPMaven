package fr.tp.rossi.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.tp.rossi.common.PersistentObjectImpl;
import fr.tp.rossi.model.MBookmark;
import fr.tp.rossi.model.MTag;
import fr.tp.rossi.model.dao.BookmarkDAO;
import fr.tp.rossi.model.dao.CommonDAO;
import fr.tp.rossi.model.dao.TagDAO;
import fr.tp.rossi.service.IServiceBookmark;

@Service
public class ServiceBookmark implements IServiceBookmark {

	public PersistentObjectImpl save(final PersistentObjectImpl newInstance) {
		return (new CommonDAO()).save(newInstance);
	}

	public PersistentObjectImpl update(final PersistentObjectImpl updatedInstance) {
		return (new CommonDAO()).update(updatedInstance);
	}

	public PersistentObjectImpl delete(final PersistentObjectImpl instanceToDelete) {
		return (new CommonDAO()).delete(instanceToDelete);
	}

	public List<MBookmark> getAllBookmarks() {
		return (new BookmarkDAO()).findAll();
	}

	public List<MTag> getAllTags() {
		return (new TagDAO()).findAll();
	}
	
	public MBookmark findBookmarkById(Integer id) {
		return (new BookmarkDAO()).findById(id);
	}
	
	public MTag findTagById(Integer id) {
		return (new TagDAO()).findById(id);
	}
	
	public boolean deleteAllBookmarks() {
		return (new BookmarkDAO()).deleteAll();
	}
	
	public MTag createOrRetrieveTagByName(String name) {
		MTag tag = (new TagDAO()).findByName(name);
		
		// Si le tag n'existe pas
		if (tag == null)
		{
			tag = new MTag();
			tag.setName(name);
			save(tag);
		}
		return tag;
	}
	public MTag findTagByName(String name) {
		return (new TagDAO()).findByName(name);
	}
}
