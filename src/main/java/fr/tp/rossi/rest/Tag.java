package fr.tp.rossi.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import com.sun.jersey.api.core.InjectParam;

import fr.tp.rossi.model.MTag;
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
	 * Retourne la liste des tags
	 * 
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public List<MTag> getTags() {
		return serviceBookmark.getAllTags();
	}

}
