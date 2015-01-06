package fr.tp.rossi.service;

import java.util.List;

import fr.tp.rossi.common.PersistentObjectImpl;
import fr.tp.rossi.model.MBookmark;
import fr.tp.rossi.model.MTag;


public interface IServiceBookmark {

	public PersistentObjectImpl save(PersistentObjectImpl newInstance);
	public PersistentObjectImpl update(PersistentObjectImpl updatedInstance);
	public PersistentObjectImpl delete(PersistentObjectImpl instanceToDelete);
	public List<MBookmark> getAllBookmarks();
	public List<MTag> getAllTags();
	public MBookmark findBookmarkById(Integer id);
	public MTag findTagById(Integer id);
	public boolean deleteAllBookmarks();
	public MTag createOrRetrieveTagByName(String name);
	public MTag findTagByName(String name);
}
