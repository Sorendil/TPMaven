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

	public List<MBookmark> getAllBookmarks() {
		return (new BookmarkDAO()).findAll();
	}

	public List<MTag> getAllTags() {
		return (new TagDAO()).findAll();
	}

}
