package gr.codehub.telco.telcoproject.resource;

import gr.codehub.telco.telcoproject.model.AppUser;
import gr.codehub.telco.telcoproject.model.User;
import gr.codehub.telco.telcoproject.service.CustomerService;
import gr.codehub.telco.telcoproject.transfer.ApiResponse;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


@Path("/login")
public class LoginResource {

    @Inject
    private CustomerService customerService;


    @Path("/")
    @POST
    @PermitAll
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(@Valid AppUser appUser){
        return Response.ok().entity(ApiResponse.builder().data(customerService.getCustomerByUserName(appUser.getUsername(), appUser.getPassword())).build()).build();
    }
}
