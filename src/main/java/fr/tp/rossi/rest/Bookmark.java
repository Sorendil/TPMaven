package fr.tp.rossi.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import com.sun.jersey.api.core.InjectParam;

import fr.tp.rossi.model.MBookmark;
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
	 * Voir http://www.vogella.com/tutorials/REST/article.html
	 * @param name
	 * @return
	 */
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String newBookmark(@FormParam("name") String name) {
		// Create new Bookmark
		MBookmark mBookmark = new MBookmark();
		if (name == null)
			throw new BadRequestException("The name is required for creating new bookmark");
		
		mBookmark.setName(name);
		
		// Enregistrement du nouveau bookmark
		serviceBookmark.save(mBookmark);

		return "Ok";
	}

}
