package gr.codehub.telco.telcoproject.exception;

import gr.codehub.telco.telcoproject.transfer.ApiError;
import gr.codehub.telco.telcoproject.transfer.ApiResponse;
import jakarta.persistence.PersistenceException;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;
import jakarta.ws.rs.InternalServerErrorException;
import jakarta.ws.rs.NotAllowedException;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.internal.util.ExceptionHelper;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;

@Provider
@Slf4j
public class GenericException implements ExceptionMapper<PersistenceException> {

    @Override
    public Response toResponse(PersistenceException e) {
        log.info("Test placeholder {}", LocalDateTime.now());
        Response.Status response = Response.Status.BAD_REQUEST;
        return Response
                .status(response).entity("This is the exception").build();

    }

}
