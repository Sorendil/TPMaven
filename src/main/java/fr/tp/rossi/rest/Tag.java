package fr.tp.rossi.rest;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
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

@Path("tag")
public class Tag {

	@Context
	private UriInfo context;

	@InjectParam
	private IServiceBookmark serviceBookmark;

	/**
	 * Construct
	 */
	public Tag() {
	}

	/**
	 * Retourne le tag d'ID id
	 * 
	 * @return
	 */
	@GET
	@Path("{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getTag(@PathParam("id") Integer id) {
		if (id == null)
			throw new BadRequestException("The tag id is required");

		MTag tag = serviceBookmark.findTagById(id);

		// Si le tag n'existe pas
		if (tag == null)
			throw new NotFoundException("The tag doesn't exist");

		return Response.status(Response.Status.OK) // Retourne code 200
				.entity(tag).build();
	}

	/**
	 * Retourne la liste des tags
	 * 
	 * @return
	 */
	@GET
	@Path("all")
	@Produces({ MediaType.APPLICATION_JSON })
	public List<MTag> getTags() {
		return serviceBookmark.getAllTags();
	}

	/**
	 * Supprime le tag de nom name
	 * 
	 * @return
	 */
	@DELETE
	@Path("{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response deleteTag(@PathParam("id") Integer id) {
		if (id == null)
			throw new BadRequestException("The tag id is required");

		MTag tag = serviceBookmark.findTagById(id);

		// Si le tag n'existe pas
		if (tag == null)
			throw new NotFoundException("The tag doesn't exist");

		// TODO : Gérer les erreurs de suppression ratées
		serviceBookmark.delete(tag);

		return Response.status(Response.Status.OK) // Retourne code 200
				.entity("Deleted").build();
	}

}
