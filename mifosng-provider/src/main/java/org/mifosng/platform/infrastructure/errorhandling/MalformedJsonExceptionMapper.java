package org.mifosng.platform.infrastructure.errorhandling;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.mifosng.platform.api.data.ApiParameterError;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.gson.stream.MalformedJsonException;

/**
 * An {@link ExceptionMapper} to map {@link MalformedJsonException} thrown by platform into a HTTP API friendly format.
 */
@Provider
@Component
@Scope("singleton")
public class MalformedJsonExceptionMapper implements ExceptionMapper<MalformedJsonException> {

	@Override
	public Response toResponse(@SuppressWarnings("unused") final MalformedJsonException exception) {
		
		String globalisationMessageCode = "error.msg.invalid.request.body";
		String defaultUserMessage = "The JSON provided in the body of the request is invalid or missing.";
		
		ApiParameterError error = ApiParameterError.generalError(globalisationMessageCode, defaultUserMessage);
		
		return Response.status(Status.BAD_REQUEST).entity(error).type(MediaType.APPLICATION_JSON).build();
	}
}