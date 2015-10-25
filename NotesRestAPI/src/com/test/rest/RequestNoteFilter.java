package com.test.rest;

import java.util.List;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerRequestFilter;
import com.test.util.NotesUtil;

public class RequestNoteFilter implements ContainerRequestFilter {

	@Override
	public ContainerRequest filter(ContainerRequest requestCtx) {

		
		String path = requestCtx.getPath();// etUriInfo().getPath();

		
		if (!path.startsWith("note")) {

			// requestCtx.abortWith(Response.status(Response.Status.OK).build());
			ResponseBuilder builder = null;
			String response = "Enter a valid Path";
			builder = Response.status(Response.Status.OK).entity(response);
			throw new WebApplicationException(builder.build());

		}

	

		List<String> emailId = requestCtx
				.getRequestHeader("emailId");
		
		List<String> password = requestCtx
				.getRequestHeader("password");
		
		if(!NotesUtil.getJDBCDAO().isUserAuthenticated(emailId.get(0), password.get(0))){
			ResponseBuilder builder = null;
			String response = "In Valid Credentails";
			builder = Response.status(Response.Status.UNAUTHORIZED).entity(
					response);
			throw new WebApplicationException(builder.build());

		}

	
		return requestCtx;
	}

}
