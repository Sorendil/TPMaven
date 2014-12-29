package fr.tp.rossi.service;

import java.util.List;

import fr.tp.rossi.model.MBookmark;


public interface IServiceBookmark {

	public MBookmark save(MBookmark newInstance);
	public List<MBookmark> getAll();
	
	public String getHtml();
}
