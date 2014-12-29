package fr.tp.rossi.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import com.sun.jersey.api.core.InjectParam;

import fr.tp.rossi.service.IServiceBookmark;


/**
 * Root resource (exposed at "helloworld" path)
 */
@Path("helloworld")
public class HelloWorld {
	@Context
	private UriInfo context;
	
	@InjectParam
	private IServiceBookmark serviceBookmark;

	/** Creates a new instance of HelloWorld */
	public HelloWorld() {
	}

	/**
	 * Retrieves representation of an instance of helloWorld.HelloWorld
	 * 
	 * @return an instance of java.lang.String
	 */
	@GET
	@Produces("text/html")
	public String getHtml() {
		return "<html lang=\"en\"><body><h1>THIS IS "
				+ serviceBookmark.getHtml()
				+ "</h1></body></html>";
	}
}