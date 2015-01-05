package fr.tp.rossi.service;

import java.util.List;

import fr.tp.rossi.common.PersistentObjectImpl;
import fr.tp.rossi.model.MBookmark;
import fr.tp.rossi.model.MTag;


public interface IServiceBookmark {

	public PersistentObjectImpl save(PersistentObjectImpl newInstance);
	public List<MBookmark> getAllBookmarks();
	public List<MTag> getAllTags();
}
