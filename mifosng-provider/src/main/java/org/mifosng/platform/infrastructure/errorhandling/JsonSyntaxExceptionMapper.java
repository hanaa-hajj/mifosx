package org.mifosng.platform.infrastructure.errorhandling;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.mifosng.platform.api.data.ApiParameterError;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.gson.JsonSyntaxException;

/**
 * An {@link ExceptionMapper} to map {@link JsonSyntaxException} thrown by platform into a HTTP API friendly format.
 */
@Provider
@Component
@Scope("singleton")
public class JsonSyntaxExceptionMapper implements ExceptionMapper<JsonSyntaxException> {

	@Override
	public Response toResponse(final JsonSyntaxException exception) {
		
		String globalisationMessageCode = "error.msg.invalid.request.body";
		String defaultUserMessage = "The JSON syntax provided in the body of the request is invalid: " + exception.getMessage();
		
		ApiParameterError error = ApiParameterError.generalError(globalisationMessageCode, defaultUserMessage);
		
		return Response.status(Status.BAD_REQUEST).entity(error).type(MediaType.APPLICATION_JSON).build();
	}
}