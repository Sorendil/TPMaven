package fr.tp.rossi.rest;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.sun.jersey.api.core.InjectParam;

import fr.tp.rossi.model.MBookmark;
import fr.tp.rossi.model.MTag;
import fr.tp.rossi.rest.exception.BadRequestException;
import fr.tp.rossi.rest.exception.NotFoundException;
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
	 * Retourne le bookmark d'ID id
	 * 
	 * @return
	 */
	@GET
	@Path("{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getBookmark(@PathParam("id") Integer id) {
		if (id == null)
			throw new BadRequestException("The bookmark id is required");

		MBookmark bookmark = serviceBookmark.findBookmarkById(id);

		// Si le bookmark n'existe pas
		if (bookmark == null)
			throw new NotFoundException("The bookmark doesn't exist");

		return Response.status(Response.Status.OK) // Retourne code 200
				.entity(bookmark).build();
	}

	/**
	 * Retourne les tag du bookmark d'ID id
	 * 
	 * @return
	 */
	@GET
	@Path("{id}/tags")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getBookmarkTags(@PathParam("id") Integer id) {
		if (id == null)
			throw new BadRequestException("The bookmark id is required");

		MBookmark bookmark = serviceBookmark.findBookmarkById(id);

		// Si le bookmark n'existe pas
		if (bookmark == null)
			throw new BadRequestException("The bookmark doesn't exist");

		return Response.status(Response.Status.OK) // Retourne code 200
				.entity(bookmark.getTags()).build();
	}

	/**
	 * Retourne la liste des bookmarks
	 * 
	 * @return
	 */
	@GET
	@Path("all")
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
	public Response newBookmark(@FormParam("name") String name,
			@FormParam("description") String description) {
		if (name == null)
			throw new BadRequestException(
					"The name is required for creating new bookmark");

		// Création du nouveau Bookmark
		MBookmark mBookmark = new MBookmark();
		mBookmark.setName(name);
		mBookmark.setDescription(description);

		// Enregistrement du nouveau bookmark
		serviceBookmark.save(mBookmark);

		return Response.status(Response.Status.CREATED) // Retourne code 201
				.entity(mBookmark).build();
	}

	/**
	 * Ajoute un tag à un bookmark
	 */
	@POST
	@Path("{id}/tag")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response newBookmarkTag(@PathParam("id") Integer id,
			@FormParam("tagName") String tagName) {
		if (id == null)
			throw new BadRequestException("The bookmark id is required");

		if (tagName == null)
			throw new BadRequestException("The tag name is required");

		MBookmark bookmark = serviceBookmark.findBookmarkById(id);

		// Si le bookmark n'existe pas
		if (bookmark == null)
			throw new NotFoundException("The bookmark doesn't exist");
		
		// Ici, on crée un tag ou on le récupère s'il existe déjà
		MTag tag = serviceBookmark.createOrRetrieveTagByName(tagName);
		
		// On le rajoute au bookmark
		Set<MTag> bookmark_tags = bookmark.getTags();
		bookmark_tags.add(tag);
		bookmark.setTags(bookmark_tags);
		
		// On actualise le tag (pour pas qu'un tag doublon apparaisse)
		bookmark = serviceBookmark.findBookmarkById(id);

		// On enregistre le bookmark
		serviceBookmark.update(bookmark);
		
		return Response.status(Response.Status.CREATED) // Retourne code 201
				.entity(bookmark).build();
	}

	/**
	 * Enleve un tag à un bookmark
	 */
	@DELETE
	@Path("{id}/tag/{tagId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response deleteBookmarkTag(@PathParam("id") Integer id,
			@PathParam("tagId") Integer tagId) {
		if (id == null)
			throw new BadRequestException("The bookmark id is required");

		if (tagId == null)
			throw new BadRequestException("The tag id is required");

		MBookmark bookmark = serviceBookmark.findBookmarkById(id);

		// Si le bookmark n'existe pas
		if (bookmark == null)
			throw new NotFoundException("The bookmark doesn't exist");
		
		// On récupère le tag
		MTag tag = serviceBookmark.findTagById(tagId);

		// Si le tag n'existe pas
		if (tag == null)
			throw new NotFoundException("The tag doesn't exist");

		// On supprime le tag du bookmark
		bookmark.removeTag(tag);

		// On enregistre le bookmark
		serviceBookmark.update(bookmark);

		
		return Response.status(Response.Status.OK) // Retourne code 200
				.entity(bookmark).build();
	}

	/**
	 * Supprime le bookmark d'ID id
	 * 
	 * @return
	 */
	@DELETE
	@Path("{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response deleteBookmark(@PathParam("id") Integer id) {
		if (id == null)
			throw new BadRequestException("The bookmark id is required");

		MBookmark bookmark = serviceBookmark.findBookmarkById(id);

		// Si le bookmark n'existe pas
		if (bookmark == null)
			throw new NotFoundException("The bookmark doesn't exist");

		// TODO : Gérer les erreurs de suppression ratées
		serviceBookmark.delete(bookmark);

		return Response.status(Response.Status.OK) // Retourne code 200
				.entity("Deleted").build();
	}

	/**
	 * Supprime tous les bookmarks
	 * 
	 */
	@DELETE
	@Path("all")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response deleteAllBookmarks() {
		serviceBookmark.deleteAllBookmarks();

		return Response.status(Response.Status.OK) // Retourne code 200
				.entity("Deleted").build();
	}

}
