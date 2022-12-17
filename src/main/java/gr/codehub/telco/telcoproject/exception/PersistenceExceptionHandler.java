package gr.codehub.telco.telcoproject.exception;

import gr.codehub.telco.telcoproject.transfer.ApiError;
import gr.codehub.telco.telcoproject.transfer.ApiResponse;
import jakarta.persistence.PersistenceException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import lombok.extern.slf4j.Slf4j;
import java.time.LocalDateTime;

@Provider
@Slf4j
public class PersistenceExceptionHandler extends PersistenceException implements ExceptionMapper<PersistenceException> {

    @Override
    public Response toResponse(PersistenceException exception) {
        log.info("Test placeholder {}", LocalDateTime.now());
        Response.Status response = Response.Status.BAD_REQUEST;
        return Response
                .status(response)
                .entity(
                        ApiResponse.builder().apiError(
                                        new ApiError(response.getStatusCode(),exception.getMessage())
                                )
                                .build()

                ).build();
    }

}
