package gr.codehub.telco.telcoproject.exception;


import gr.codehub.telco.telcoproject.transfer.ApiError;
import gr.codehub.telco.telcoproject.transfer.ApiResponse;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;


@Provider
@Slf4j
public class CustomerPropertiesExistingExceptionHandler implements ExceptionMapper<CustomerPropertiesExistingException> {
    @Override
    public Response toResponse(CustomerPropertiesExistingException exception) {
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
