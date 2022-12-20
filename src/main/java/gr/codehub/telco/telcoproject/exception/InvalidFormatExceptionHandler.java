package gr.codehub.telco.telcoproject.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import gr.codehub.telco.telcoproject.transfer.ApiError;
import gr.codehub.telco.telcoproject.transfer.ApiResponse;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import lombok.extern.slf4j.Slf4j;
import java.time.LocalDateTime;

@Slf4j
@Provider
public class InvalidFormatExceptionHandler implements ExceptionMapper<InvalidFormatException> {

    @Override
    public Response toResponse(InvalidFormatException exception) {
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
