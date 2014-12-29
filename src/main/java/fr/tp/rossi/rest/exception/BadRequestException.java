package fr.tp.rossi.rest.exception;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

/**
 * Throw 400 Bad Request Exception
 * 
 * @author Anthony, from
 *         http://zcox.wordpress.com/2009/06/22/use-exceptions-to-send
 *         -error-responses-in-jersey/
 *
 */
public class BadRequestException extends WebApplicationException {
	private static final long serialVersionUID = 1L;
	private List<String> errors;

	public BadRequestException(String... errors) {
		this(Arrays.asList(errors));
	}

	public BadRequestException(List<String> errors) {
		super(Response.status(Status.BAD_REQUEST)
				.type(MediaType.APPLICATION_JSON)
				.entity(new GenericEntity<List<String>>(errors) {
				}).build());
		this.errors = errors;
	}

	public List<String> getErrors() {
		return errors;
	}
}
