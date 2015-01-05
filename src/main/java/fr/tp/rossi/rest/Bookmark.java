package fr.tp.rossi.rest;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import com.sun.jersey.api.core.InjectParam;

import fr.tp.rossi.model.MBookmark;
import fr.tp.rossi.model.MTag;
import fr.tp.rossi.rest.exception.BadRequestException;
import fr.tp.rossi.service.IServiceBookmark;

/**
 * Root resource (exposed at "bookmark" path)
 */
@Path("bookmark")
public class Bookmark {

	@Context
	private UriInfo context;

	@InjectParam
	private IServiceBookmark serviceBookmark;

	/**
	 * Construct
	 */
	public Bookmark() {
	}

	/**
	 * Retourne la liste des bookmarks
	 * 
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public List<MBookmark> getBookmarks() {
		return serviceBookmark.getAllBookmarks();
	}

	/**
	 * Voir http://www.vogella.com/tutorials/REST/article.html
	 * 
	 * @param name
	 * @return
	 */
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public MBookmark newBookmark(@FormParam("name") String name,
			@FormParam("description") String description,
			@FormParam("tag") String tag) {
		if (name == null)
			throw new BadRequestException(
					"The name is required for creating new bookmark");

		// Création du nouveau Bookmark
		MBookmark mBookmark = new MBookmark();
		mBookmark.setName(name);
		mBookmark.setDescription(description);
		
		if (tag == null)
		{
			MTag newTag = new MTag();
			newTag.setName(tag);
			// Pour tester
			// serviceBookmark.save(newTag);
			Set<MTag> bookmark_tags = new HashSet<MTag>();
			bookmark_tags.add(newTag);
			mBookmark.setTags(bookmark_tags);
		}

		// Enregistrement du nouveau bookmark
		serviceBookmark.save(mBookmark);

		return mBookmark;
	}

}
