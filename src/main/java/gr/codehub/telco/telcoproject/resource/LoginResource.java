package gr.codehub.telco.telcoproject.resource;

import gr.codehub.telco.telcoproject.service.CustomerService;
import gr.codehub.telco.telcoproject.transfer.ApiResponse;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


@Path("/login")
public class LoginResource {

    @Inject
    private CustomerService customerService;


    @Path("/{username}")
    @GET
    @RolesAllowed({"ADMIN","CUSTOMER"})
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(@PathParam("username") String username){
        return Response.ok().entity(ApiResponse.builder().data(customerService.getCustomerByUserName(username)).build()).build();
    }
}
